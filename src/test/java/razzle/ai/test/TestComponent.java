package razzle.ai.test;

import org.springframework.stereotype.Component;
import razzle.ai.annotation.ActionParam;
import razzle.ai.annotation.Action;

/**
 * created by julian on 09/02/2023
 */
@Component
public class TestComponent {


    @Action(
        name = "testAction",
        description = "test razzle action"
    )
    public String testingActions(@ActionParam String name) {
        return "Hello " + name;
    }


}
