package org.keith.model;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * A class representing a Google Spreadsheet with one or more sheets in them
 *
 * The class is configured through the spreadsheets.json file in resources
 *
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
@Data
public class Spreadsheet {
    String name;
    String id;
    List<Sheet> sheets;

    public Sheet findSheet(@NonNull String name) {
        Optional<Sheet> sheet = sheets.stream().filter(s -> s.name.equalsIgnoreCase(name)).findFirst();
        return sheet.isPresent() ? sheet.get() : null;
    }
}
