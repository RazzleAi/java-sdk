package razzle.ai.api.widget;

import java.util.List;

/**
 * created by Julian Duru on 26/02/2023
 */
public interface IRazzleTable extends IRazzleWidget {


    List<IRazzleTableColumn> getColumns();

    String[][] getData();

    boolean isShowPagination();


}

