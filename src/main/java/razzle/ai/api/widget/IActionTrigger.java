package razzle.ai.api.widget;

/**
 * created by Julian Duru on 26/02/2023
 */
public interface IActionTrigger {

    String getAction();

    String getLabel();

    Object[] getArgs();

    Type getType();


    enum Type {

        RazzleAction,

        URL

    }


}

