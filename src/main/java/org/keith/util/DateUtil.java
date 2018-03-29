package org.keith.util;

import lombok.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
public class DateUtil {
    public static Date parseDateString(String dateString)  {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static LocalDate toLocalDate(@NonNull String dateString) {
        try {
            return parseDateString(dateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }
}
