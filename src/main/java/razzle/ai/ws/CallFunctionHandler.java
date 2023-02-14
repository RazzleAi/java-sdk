package razzle.ai.ws;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import razzle.ai.api.ActionPlanWithDetails;
import razzle.ai.api.ServerMessage;
import razzle.ai.api.ServerMessageType;
import razzle.ai.api.ServerRequest;
import razzle.ai.util.JSONUtil;

/**
 * created by julian on 12/02/2023
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CallFunctionHandler implements TextMessageHandler {



    @Override
    public ServerRequest<?> handleTextMessage(String message) throws Exception {
        log.info("Call Function: {}", message);
        var messageObject = JSONUtil.fromJsonString(message, ServerMessage.class);
        var headers = messageObject.getData().getHeaders();
        var data = JSONUtil.asObjectOfClass(
            messageObject.getData().getPayload(), ActionPlanWithDetails.class
        );
        return null;
    }


    @Override
    public boolean canHandleMessageType(ServerMessageType type) {
        return type == ServerMessageType.CallFunction;
    }


}

