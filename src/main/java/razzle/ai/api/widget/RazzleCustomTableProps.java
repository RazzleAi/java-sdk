package razzle.ai.api.widget;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * created by Julian Duru on 01/03/2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RazzleCustomTableProps {

    private String title;

    private List<RazzleCustomTableColumnProps> columns;

    private List<RazzleCustomTableRowProps> rows;


}
