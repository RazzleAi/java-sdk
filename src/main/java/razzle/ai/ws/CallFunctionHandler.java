package razzle.ai.ws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import razzle.ai.api.*;
import razzle.ai.api.widget.RazzleResponse;
import razzle.ai.context.RazzleActionHandlersContainer;
import razzle.ai.util.JSONUtil;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * created by Julian Duru on 12/02/2023
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallFunctionHandler implements TextMessageHandler {


    private final RazzleActionHandlersContainer handlersContainer;


    @Override
    public ServerRequest<?> handleTextMessage(String message) throws Exception {
        log.info("Call Function: {}", message);
        var messageObject = JSONUtil.fromJsonString(message, ServerMessage.class);
        var headers = messageObject.getData().getHeaders();
        var data = JSONUtil.asObjectOfClass(
            messageObject.getData().getPayload(), ActionPlanWithDetails.class
        );
        var handlerOptional = handlersContainer.getHandler(data.getActionName());
        if (handlerOptional.isEmpty()) {
            throw new IllegalArgumentException("No handler found for action: " + data.getActionName());
        }

        var handler = handlerOptional.get();
        var callDetails = CallDetails.builder()
            .accountId(headers.get("accountId").toString())
            .userId(headers.get("userId").toString())
            .workspaceId(headers.get("workspaceId").toString())
            .context((LinkedHashMap<String, Object>) headers.get("context"))
            .build();
        var args = data.getArgs();
        var argsList = Arrays.asList(args);
        var methodParams = argsList.stream().map(
            ActionPlanArgsString::getValue
        ).toList();
        var methodParamArray = methodParams.toArray(new String[]{});

        var handlerMethod = handler.getMethod();
        var handlerMethodParams = handlerMethod.getParameters();
        // check if handlerMethodParams has a CallDetails param
        var hasCallDetailsParam = Arrays.stream(handlerMethodParams).anyMatch(
            param -> param.getType() == CallDetails.class
        );
        Object[] actionParams;
        if (hasCallDetailsParam) {
            actionParams = new Object[methodParamArray.length + 1];
            actionParams[actionParams.length - 1] = callDetails;
        }
        else {
            actionParams = new Object[methodParamArray.length];
        }

        System.arraycopy(methodParamArray, 0, actionParams, 0, methodParamArray.length);

        var response = handler.getMethod().invoke(handler.getBean(), actionParams);
        log.info("Response: {}", response);

        if (!(response instanceof RazzleResponse razzleResponse)) {
            throw new IllegalStateException("Response must be of type RazzleResponse");
        }

        razzleResponse.setClientId(headers.get("clientId").toString());

        return new ServerRequest<RazzleResponse>(ServerRequestType.FuncResponse)
            .addObjectHeaders(headers)
            .setPayload(razzleResponse);
    }


    @Override
    public boolean canHandleMessageType(ServerMessageType type) {
        return type == ServerMessageType.CallFunction;
    }


}

