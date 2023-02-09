package razzle.ai;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import razzle.ai.config.RazzleConfig;
import razzle.ai.api.ServerRequest;
import razzle.ai.api.ServerRequestType;
import razzle.ai.api.SyncAppPayload;
import razzle.ai.context.RazzleActionHandlersContainer;
import razzle.ai.ws.MessageHandler;
import razzle.ai.ws.WSClientContainer;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

/**
 * created by julian on 09/02/2023
 */
@Component
@RequiredArgsConstructor
public class Razzle {

    @Autowired(required = false)
    private AuthenticationFunction authenticationFunction;

    private final RazzleConfig razzleConfig;

    private final MessageHandler messageHandler;

    private final RazzleActionHandlersContainer actionHandlersContainer;

    private WSClientContainer clientContainer;



    @PostConstruct
    public void init() {
        validateConfiguration();

        clientContainer = new WSClientContainer(razzleConfig, messageHandler);
        clientContainer.start();

        await()
            .atMost(Duration.ofSeconds(60))
            .until(() -> clientContainer.isConnected());

        registerActions();
    }


    private void validateConfiguration() throws IllegalStateException {
        if (razzleConfig.isRequiresAuth() && authenticationFunction == null) {
            throw new IllegalStateException("Authentication function is required");
        }
    }


    private void registerActions() {
        var actionHandlers = actionHandlersContainer.getHandlers();

        clientContainer.send(
            new ServerRequest<>(
                ServerRequestType.SyncApp,
                SyncAppPayload.builder()
                    .sdkVersion(RazzleConstants.SDK_VERSION)
                    .requiresAuth(razzleConfig.isRequiresAuth())
                    .actions(actionHandlers)
                    .build()
            )
        );
    }




}

