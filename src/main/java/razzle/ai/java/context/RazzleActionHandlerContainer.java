package razzle.ai.java.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import razzle.ai.java.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * created by julian on 09/02/2023
 */
@Slf4j
@Component
public class RazzleActionHandlerContainer {


    private final Map<String, Handler> handlers = new HashMap<>();


    public void registerHandler(String name, Handler handler) {
        if (handlers.containsKey(name)) {
            throw new IllegalStateException("Duplicate Action Name: " + name);
        }

        log.debug("Registering handler: {}", name);
        handlers.put(name, handler);
    }


}
