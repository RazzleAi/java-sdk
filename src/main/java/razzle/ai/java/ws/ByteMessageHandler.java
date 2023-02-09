package razzle.ai.java.ws;

import razzle.ai.java.api.ServerRequest;

import java.nio.ByteBuffer;

/**
 * created by julian on 09/02/2023
 */
interface ByteMessageHandler {


    ServerRequest<?> handleByteMessage(ByteBuffer message);


}
