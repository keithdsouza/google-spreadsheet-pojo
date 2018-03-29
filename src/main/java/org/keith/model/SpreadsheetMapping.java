package org.keith.model;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * A class that finds the details for a particular sheet in a spreadsheet
 *
 * This will return the mappigns if it finds it for a sheet, or try to find the default mapping
 * named with "Default" for that particular spreadsheet. The "Default" use-case is when you
 * have several sheets (like monthly sales) in the same spreadsheet and all of them share the
 * same mappings. As such you can simply define a single mapping named "Default" for all the sheets.
 *
 * This obviously makes "Default" a reserved sheet name and may be changed in future versions
 *
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
@Data
public class SpreadsheetMapping {
    String name;
    List<SheetMapping> sheetMappings;

    public SheetMapping getMapping(String name) {
        return search(name).orElse(search("Default").orElse(null));
    }

    private Optional<SheetMapping> search(String name) {
        return getSheetMappings().stream().filter(s -> s.getName().contains(name)).findFirst();
    }
}
