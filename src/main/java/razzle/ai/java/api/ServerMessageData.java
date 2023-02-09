package razzle.ai.java.api;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * created by julian on 09/02/2023
 */
@Data
public class ServerMessageData<T> {


    private Map<String, String> headers;


    private T payload;


    public ServerMessageData<T> addHeader(String key, String value) {
        if (headers == null) {
            headers = new HashMap<>();
        }

        headers.put(key, value);
        return this;
    }


}

