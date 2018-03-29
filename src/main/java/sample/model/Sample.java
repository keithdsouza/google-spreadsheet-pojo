package sample.model;

import lombok.Data;
import org.keith.model.SpreadsheetRow;

import java.util.Date;

/**
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
@Data
public class Sample extends SpreadsheetRow {
    String product;
    String store;
    Integer quantity;
    Double price;
    Double percent;
    Date date;
    Status status;
}
