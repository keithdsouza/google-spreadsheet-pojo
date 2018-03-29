package org.keith.connector;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keith Dsouza
 *         Created on 6/13/16.
 */
@Slf4j
public class SpreadsheetService {

    private Sheets service;

    public SpreadsheetService() throws IOException {
        service = GoogleConnector.getSheetsService();
    }

    public ValueRange get(String spreadsheetId, String range) throws IOException {
        return service.spreadsheets().values().get(spreadsheetId, range).execute();
    }

    public Spreadsheet create(Spreadsheet spreadsheet) throws IOException {
        return service.spreadsheets().create(spreadsheet).execute();
    }

    public void append(String spreadsheetId, String range, List<List<Object>> data) throws IOException {
        ValueRange valueRange = new ValueRange().setValues(data).setMajorDimension("ROWS");
        service.spreadsheets().values().append(spreadsheetId, range, valueRange).setValueInputOption("RAW").execute();
    }

    public BatchUpdateValuesResponse update(String spreadsheetId, BatchUpdateValuesRequest updateRequest) throws IOException {
        return service.spreadsheets().values().batchUpdate(spreadsheetId, updateRequest).execute();
    }

    public void batchUpdate(String spreadsheetId, BatchUpdateSpreadsheetRequest request) throws IOException {
        service.spreadsheets().batchUpdate(spreadsheetId, request).execute();
    }

    public void deleteRows(String spreadsheetId, int start, int end) throws IOException {
        List<Request> requests = new ArrayList<>();
        requests.add(new Request().setDeleteDimension(new DeleteDimensionRequest()
                .setRange(new DimensionRange().setStartIndex(start).setEndIndex(end).setDimension("ROWS").setSheetId(0))));
        batchUpdate(spreadsheetId, new BatchUpdateSpreadsheetRequest().setRequests(requests));
    }
}
