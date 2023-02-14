package razzle.ai.api;

import lombok.Data;

/**
 * created by julian on 14/02/2023
 */
@Data
public class ActionPlanWithDetails {


    private String uuid;

    private String appId;

    private String appName;

    private String appDescription;

    private String actionName;

    private ActionPlanArgsString[] args;

}

@Data
class ActionPlanArgsString {

    private String name;

    private String value;

    private String type;

}


@Data
class ActionPlanArgsPlanWithDetails {

    private String name;

    private ActionPlanWithDetails value;

    private String type;

}

