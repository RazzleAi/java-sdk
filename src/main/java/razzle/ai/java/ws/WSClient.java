package razzle.ai.java.ws;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import razzle.ai.java.RazzleConstants;
import razzle.ai.java.config.RazzleConfig;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * created by julian on 09/02/2023
 */
@Slf4j
public class WSClient extends WebSocketClient {


    private static final String SERVER_URI = "ws://localhost:3333/agent";


    private MessageHandler messageHandler;


    public WSClient(RazzleConfig config, MessageHandler messageHandler) {
        super(
            URI.create(SERVER_URI), config.defaultHeadersMap()
        );
    }


    @PostConstruct
    public void init() {
        log.info("Initializing Web Socket Client");
        connect();
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("Successfully Established Connection to Razzle Server");
    }


    @Override
    public void onMessage(String s) {
        log.debug("Message Received: {}", s);
        messageHandler.handleTextMessage(s);
    }


    @Override
    public void onMessage(ByteBuffer bytes) {
        log.debug("Message Received: {}", bytes);
    }


    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("Successfully Closed Connection to Razzle Server");
    }


    @Override
    public void onError(Exception e) {
        log.error("Web Socket encountered an error", e);
    }


}


