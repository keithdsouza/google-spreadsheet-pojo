package org.keith.model;

import lombok.Data;

/**
 * A class representing a Sheet in a Google Spreadsheet
 *
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
@Data
public class Sheet {
    String name;
    String range;

    public String getSheetRange() {
        return String.format("%s!%s", getName(), getRange());
    }
}
