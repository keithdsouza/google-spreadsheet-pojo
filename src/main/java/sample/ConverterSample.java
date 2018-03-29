package sample;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.keith.connector.SpreadsheetService;
import org.keith.converter.RowToPOJOConverter;
import org.keith.model.Sheet;
import org.keith.model.SheetMapping;
import org.keith.model.Spreadsheet;
import org.keith.model.SpreadsheetMapping;
import org.keith.util.CommonUtil;
import org.keith.util.Loader;
import sample.model.Sample;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Keith Dsouza
 * Created on 3/26/18.
 */
@Slf4j
@Getter
public class ConverterSample {
    private final SpreadsheetService service;
    private final Map<String, Spreadsheet> spreadsheets;
    private final Map<String, SpreadsheetMapping> mappings;

    public ConverterSample() throws Exception {
        //this will prompt you to login if you haven't in the past
        this.service = new SpreadsheetService();
        //collect the list of all the spreadsheets we have setup, this is required because we have id's in them
        List<Spreadsheet> settingsSheets = Loader.loadSpreadsheets("spreadsheets.json");
        this.spreadsheets = settingsSheets.stream().collect(Collectors.toMap(s -> s.getName(), Function.identity()));

        //collect all the mappings from an individual sheet in a spreadsheet to a POJO field name
        List<SpreadsheetMapping> sheetMappings = Loader.loadMappings("sheet-to-pojo.json");
        this.mappings = sheetMappings.stream().collect(Collectors.toMap(s -> s.getName(), Function.identity()));
    }

    public static void main(String[] args) throws Exception {
        ConverterSample converter = new ConverterSample();

        String spreadSheetName = "SampleSpreadsheet";
        String sheetName = "SampleSheet";

        //get the spreadsheet details for which we want to work with, usually you will call a central service to get this
        Spreadsheet spreadsheet = converter.getSpreadsheets().get(spreadSheetName);

        //get the sheet in this spreadsheet we want to get data from
        Sheet sheet = spreadsheet.findSheet(sheetName);

        List<List<Object>> data = converter.getService()
                .get(spreadsheet.getId(), sheet.getRange()).getValues();

        //get all mappings for all spreadsheets, usually you will call a central service to get this
        SpreadsheetMapping mappingToWorkWith = converter.getMappings().get(spreadSheetName);
        SheetMapping mapping = mappingToWorkWith.getMapping(sheetName);

        Map<String, Integer> header = CommonUtil.getHeader(data.get(0));
        //remove header row
        data.remove(0);
        List<Sample> samples = RowToPOJOConverter.convert(Sample.class, spreadsheet, sheet, data, header, mapping.getMappings(), null);
        log.info("Purchases are {}", samples);

    }
}
