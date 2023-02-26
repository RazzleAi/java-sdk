package razzle.ai.api.widget;

import lombok.Getter;

/**
 * created by Julian Duru on 26/02/2023
 */
@Getter
public class RazzleLink extends RazzleWidget implements IRazzleLink {

    private final IActionTrigger action;

    private final RazzleTextSize textSize;


    public RazzleLink(RazzleLinkProps props) {
        this.action = props.getAction();
        this.textSize = props.getTextSize() != null ? props.getTextSize() : RazzleTextSize.medium;
    }


    @Override
    public String getType() {
        return RazzleWidgetType.LINK.getValue();
    }


    @Override
    public IRazzleLink toJSON() {
        return this;
    }


}
