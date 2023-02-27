package razzle.ai.api.widget;

import lombok.Getter;

import java.util.List;

/**
 * created by Julian Duru on 26/02/2023
 */
@Getter
public class RazzleTable extends RazzleWidget implements IRazzleTable<RazzleTableColumn> {


    private final List<RazzleTableColumn> columns;

    private final String[][] data;

    private final boolean showPagination;


    public RazzleTable(RazzleTableProps props) {
        if (props.getData().length > 0) {
            if (props.getData()[0].length != props.getColumns().size()) {
                throw new IllegalArgumentException("Column length must match data length");
            }
        }

        this.columns = props.getColumns().stream().map(RazzleTableColumn::new).toList();
        this.data = props.getData();
        this.showPagination = props.isShowPagination();
    }


    @Override
    public String getType() {
        return RazzleWidgetType.TABLE.getValue();
    }


    @Override
    public RazzleTable toJSON() {
        return this;
    }


}



