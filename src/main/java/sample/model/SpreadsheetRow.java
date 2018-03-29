package sample.model;

import lombok.Data;
import org.keith.model.Sheet;
import org.keith.model.Spreadsheet;

import java.util.Map;

/**
 * Provides metadata about a POJO created through a Sheet from a Spreadsheet -> Sheet
 * @author Keith Dsouza
 * Created on 3/28/18.
 */
@Data
public class SpreadsheetRow {
    Spreadsheet spreadsheet;
    Sheet sheet;
    Map<String, String> mapping;
    Map<String, String> inverseMapping;
    Map<String, Integer> header;
    int row;
}
