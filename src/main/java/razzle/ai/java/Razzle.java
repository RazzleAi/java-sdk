package razzle.ai.java;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import razzle.ai.java.config.RazzleConfig;
import razzle.ai.java.ws.MessageHandler;
import razzle.ai.java.ws.WSClientContainer;

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

    private WSClientContainer clientContainer;


    @PostConstruct
    public void init() {
        validateConfiguration();

        clientContainer = new WSClientContainer(razzleConfig, messageHandler);
        clientContainer.start();
    }


    private void validateConfiguration() throws IllegalStateException {
        if (razzleConfig.isRequiresAuth() && authenticationFunction == null) {
            throw new IllegalStateException("Authentication function is required");
        }
    }


}

