package razzle.ai.api.widget;

import lombok.Data;

import java.util.List;

/**
 * created by Julian Duru on 25/02/2023
 */
@Data
public abstract class RazzleWidget implements IRazzleWidget {


    private List<RazzleWidget> children;

    public abstract String getType();

    public abstract IRazzleWidget toJSON();


}
