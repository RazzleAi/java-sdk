package razzle.ai.test;

import org.springframework.stereotype.Component;
import razzle.ai.annotation.ActionParam;
import razzle.ai.annotation.Action;
import razzle.ai.api.CallDetails;

/**
 * created by julian on 09/02/2023
 */
@Component
public class TestComponent {


    @Action(
        name = "testAction",
        description = "test razzle action"
    )
    public String testingActions(CallDetails callDetails, @ActionParam String name) {
        return "Hello " + name;
    }


}
