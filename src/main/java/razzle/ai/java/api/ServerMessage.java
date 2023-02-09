package razzle.ai.java.api;

import lombok.Data;

/**
 * created by julian on 09/02/2023
 */
@Data
public class ServerMessage<T> {


    private ServerMessageType event;


    private ServerMessageData<T> data;


}
