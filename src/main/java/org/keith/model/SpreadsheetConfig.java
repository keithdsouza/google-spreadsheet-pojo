package org.keith.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * An object to create a spreadsheet configuration used for extracting data
 *
 * @author Keith Dsouza
 * Created on 3/26/18.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpreadsheetConfig {
    String name;
    List<String> sheets;
    RowFilter filter;

    public RowFilter getFilter() {
        if(filter == null) {
            filter = row -> true;
        }
        return filter;
    }
}
