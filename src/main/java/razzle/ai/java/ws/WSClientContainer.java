package razzle.ai.java.ws;

import lombok.extern.slf4j.Slf4j;
import razzle.ai.java.api.ServerRequest;
import razzle.ai.java.config.RazzleConfig;
import razzle.ai.java.exception.SenderException;
import razzle.ai.java.util.JSONUtil;

/**
 * created by julian on 09/02/2023
 */
@Slf4j
public class WSClientContainer {

    private int retryCount;

    private int backoffTime;

    private static int MAX_RETRY_COUNT = 50;

    private final RazzleConfig razzleConfig;

    private final MessageHandler messageHandler;

    private final WSClient client;


    public WSClientContainer(RazzleConfig razzleConfig, MessageHandler messageHandler) {
        initializeReconnectionParameters();

        this.razzleConfig = razzleConfig;
        this.messageHandler = messageHandler;

        this.client = new WSClient(razzleConfig, messageHandler);
    }


    public boolean isConnected() {
        return client.isOpen();
    }


    public void start() {
        if (!isConnected()) {
            log.info("Starting Web Socket Client");
            this.client.connect();
        }
    }


    private void reconnect() {
        try {
            if (retryCount < MAX_RETRY_COUNT) {
                log.info("Attempting to reconnect to Razzle Server");
                client.reconnectBlocking();
                initializeReconnectionParameters();
            } else {
                log.error("Maximum number of retries exceeded. Unable to connect to Razzle Server");
            }
        }
        catch (InterruptedException e) {
            log.error("Error while reconnecting WebSocket", e);
            retryCount++;
            backoffTime *= 2;
        }
    }


    private void initializeReconnectionParameters() {
        retryCount = 0;
        backoffTime = 2000;
    }


    public void send(ServerRequest<?> message) throws SenderException {
        try {
            if (!isConnected()) {
                log.warn("Server is not connected. Attempting to reconnect");
                reconnect();
                return;
            }

            message.addHeaders(razzleConfig.defaultHeadersMap());
            var messageJson = JSONUtil.asJsonString(message);

            log.debug("Sending message to Razzle Server: {}", messageJson);
            client.send(messageJson);
        }
        catch (Throwable t) {
            log.error("Error while sending message", t);
            throw new SenderException(t);
        }
    }


    private void sendPing() {
        if (!this.isConnected()) {
            return;
        }

        client.sendPing();
    }


}

