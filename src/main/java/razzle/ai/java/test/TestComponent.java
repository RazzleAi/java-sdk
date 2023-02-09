package razzle.ai.java.test;

import org.springframework.stereotype.Component;
import razzle.ai.java.annotation.Action;
import razzle.ai.java.annotation.ActionParam;

/**
 * created by julian on 09/02/2023
 */
@Component
public class TestComponent {


    @Action(
        name = "testAction",
        description = "testing razzle actions"
    )
    public String testingActions(@ActionParam String name) {
        return "Hello " + name;
    }


}
