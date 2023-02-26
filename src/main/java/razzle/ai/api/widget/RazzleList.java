package razzle.ai.api.widget;

import lombok.Getter;

import java.util.List;

/**
 * created by Julian Duru on 26/02/2023
 */
@Getter
public class RazzleList extends RazzleWidget implements IRazzleList {


    private final String title;


    private final List<RazzleListItem> items;


    public RazzleList(RazzleListProps props) {
        this.title = props.getTitle();
        this.items = props.getItems().stream()
            .map(RazzleListItem::new)
            .toList();
    }


    @Override
    public String getType() {
        return RazzleWidgetType.LIST.getValue();
    }


    @Override
    public IRazzleList toJSON() {
        return this;
    }


}

