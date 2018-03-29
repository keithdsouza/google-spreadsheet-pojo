package org.keith.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.keith.model.Spreadsheet;
import org.keith.model.SpreadsheetMapping;

import java.util.List;

/**
 * Unfortunately Jackson does not support generic types to we have to load things indivdiaull
 *
 * @see https://github.com/FasterXML/jackson-databind/issues/1490
 *
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
@Slf4j
public class Loader {
    public static List<Spreadsheet> loadSpreadsheets(String fileName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(ResourceLoader.get(fileName), new TypeReference<List<Spreadsheet>>(){});
    }

    public static List<SpreadsheetMapping> loadMappings(String fileName) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(ResourceLoader.get(fileName), new TypeReference<List<SpreadsheetMapping>>(){});
    }
}
