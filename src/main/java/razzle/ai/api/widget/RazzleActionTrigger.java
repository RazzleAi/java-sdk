package razzle.ai.api.widget;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * created by Julian Duru on 27/02/2023
 */
@Data
@AllArgsConstructor
public class RazzleActionTrigger implements IActionTrigger {

    private Type type;

    private String action;

    private String label;

    private Object[] args;

}
