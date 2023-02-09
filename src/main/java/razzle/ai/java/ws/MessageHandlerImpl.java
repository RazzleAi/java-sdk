package razzle.ai.java.ws;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import razzle.ai.java.api.ServerMessage;
import razzle.ai.java.exception.ReceiverException;
import razzle.ai.java.util.JSONUtil;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * created by julian on 09/02/2023
 */
@Component
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {


    private final List<TextMessageHandler> textMessageHandlers;



    @Override
    public void handleTextMessage(String message) throws ReceiverException {
        try {
            TextMessageHandler selectedHandler = null;
            var messageObject = JSONUtil.fromJsonString(message, ServerMessage.class);

            for (TextMessageHandler handler : textMessageHandlers) {
                if (handler.canHandleMessage(messageObject)) {
                    selectedHandler = handler;
                    break;
                }
            }

            if (selectedHandler == null) {
                throw new IllegalArgumentException("No handler found for message: " + message);
            }

            selectedHandler.handleTextMessage(message);
        }
        catch (Throwable e) {
            throw new ReceiverException("Error while handling message: " + message, e);
        }
    }


    @Override
    public void handleByteMessage(ByteBuffer message) throws ReceiverException {
        throw new UnsupportedOperationException("Byte messages are not supported yet");
    }


}

