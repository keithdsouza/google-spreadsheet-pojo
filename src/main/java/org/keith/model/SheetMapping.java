package org.keith.model;

import lombok.Data;

import java.util.Map;

/**
 * The core for creating an object from the Google Spreadsheet sheet, this contains a mapping from a sheet header to the POJO
 * property.
 *
 * For example, if the column header in the sheet is name "Name" and the POJO property is named "starName", you can map that column
 * data to the POJO by providing a mapping in the sheet-to-pojo.json as
 *
 * "Name": "starName"
 *
 * Adding this property will automatically map the column to the POJO property
 *
 *
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
@Data
public class SheetMapping {
    String name;
    Map<String, String> mappings;
}
