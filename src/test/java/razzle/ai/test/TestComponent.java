package razzle.ai.test;

import org.springframework.stereotype.Component;
import razzle.ai.annotation.ActionParam;
import razzle.ai.annotation.Action;
import razzle.ai.api.CallDetails;
import razzle.ai.api.widget.*;

/**
 * created by julian on 09/02/2023
 */
@Component
public class TestComponent {


    @Action(
        name = "testAction",
        description = "test a razzle action"
    )
    public RazzleResponse testingActions(@ActionParam String name, CallDetails callDetails) {
        return RazzleResponse.of(
            new RazzleText(
                RazzleTextProps.builder()
                    .text("Hello " + name)
                    .build()
            )
        );
    }


    @Action(
        name = "testAction2",
        description = "test a razzle action 2"
    )
    public RazzleResponse testingActions2(@ActionParam String name, @ActionParam String email, CallDetails callDetails) {
        return RazzleResponse.of(
            new RazzleText(
                RazzleTextProps.builder()
                    .text("Hello " + name + " your email is " + email)
                    .padding(WidgetPadding.DEFAULT)
                    .build()
            )
        );
    }


    @Action(
        name = "callLinkAction",
        description = "call a razzle link action"
    )
    public RazzleResponse composeLink(@ActionParam String name) {
        return RazzleResponse.of(
            new RazzleLink(
                RazzleLinkProps.builder()
                    .action(
                        RazzleActionTrigger.of(
                            IActionTrigger.Type.URL,
                            "http://localhost:8080/testAction?name=" + name,
                            "Follow Link"
                        )
                    )
                    .textSize(RazzleTextSize.medium)
                    .build()
            )
        );
    }


    @Action(
        name = "callListAction",
        description = "call a razzle list action"
    )
    public RazzleResponse composeList() {
        return RazzleResponse.of(
            new RazzleList(
                RazzleListProps.of(
                    "Call List Action",

                    RazzleListItemProps.builder()
                        .text("Item 1")
                        .onSelect(
                            RazzleActionTrigger.of(
                                IActionTrigger.Type.URL,
                                "http://localhost:8080/testAction?name=Item 1",
                                "Follow Link"
                            )
                        )
                        .build(),

                    RazzleListItemProps.builder()
                        .text("Item 2")
                        .onSelect(
                            RazzleActionTrigger.of(
                                IActionTrigger.Type.URL,
                                "http://localhost:8080/testAction?name=Item 2",
                                "Follow Link"
                            )
                        )
                        .build(),

                    RazzleListItemProps.builder()
                        .text("Item 3")
                        .onSelect(
                            RazzleActionTrigger.of(
                                IActionTrigger.Type.URL,
                                "http://localhost:8080/testAction?name=Item 3",
                                "Follow Link"
                            )
                        )
                        .build()
                )
            )
        );
    }


}


