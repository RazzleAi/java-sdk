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
        description = "test a razzle action"
    )
    public String testingActions(@ActionParam String name, CallDetails callDetails) {
        return "Hello " + name;
    }


    @Action(
        name = "testAction2",
        description = "test a razzle action 2"
    )
    public String testingActions2(@ActionParam String name, @ActionParam String email, CallDetails callDetails) {
        return "Hello " + name;
    }


}


