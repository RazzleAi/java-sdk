package razzle.ai.java.ws;

import razzle.ai.java.api.ServerMessage;
import razzle.ai.java.api.ServerMessageType;
import razzle.ai.java.api.ServerRequest;
import razzle.ai.java.util.JSONUtil;

/**
 * created by julian on 09/02/2023
 */
interface TextMessageHandler {


    ServerRequest<?> handleTextMessage(String message);


    default boolean canHandleMessage(ServerMessage<?> message) {
        return canHandleMessageType(message.getEvent());
    }


    default boolean canHandleMessage(String message) {
        try {
            var messageObject = JSONUtil.fromJsonString(message, ServerMessage.class);
            return canHandleMessageType(messageObject.getEvent());
        }
        catch (Throwable e) {
            return false;
        }
    }


    boolean canHandleMessageType(ServerMessageType type);


}
