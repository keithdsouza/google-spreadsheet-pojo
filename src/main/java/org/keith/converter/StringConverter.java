package org.keith.converter;

import lombok.NonNull;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.BigDecimalValidator;
import org.apache.commons.validator.routines.CurrencyValidator;
import org.keith.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
public class StringConverter {
    public static Object convert(@NonNull String value) {

        //return immediately if this a date
        BigDecimalValidator validator = CurrencyValidator.getInstance();
        BigDecimal amount = validator.validate(value, Locale.US);
        if(amount != null) {
            return toDouble(onlyDigits(value));
        }

        if(NumberUtils.isNumber(value.substring(0, value.length() - 1))) {
            value = value.substring(0, value.length() - 1);
        }

        if(NumberUtils.isNumber(value)) {
            Integer intValue = toInt(onlyDigits(value));
            Double doubleValue = toDouble(onlyDigits(value));

            if(doubleValue != null && intValue != null) {
                if(doubleValue > intValue) {
                    return doubleValue;
                }
                else {
                    return intValue;
                }
            }
            else if(doubleValue != null) {
                return doubleValue;
            }
            else if(intValue != null){
                return intValue;
            }
        }
        else if (getLocalDate(value) != null) {
            return getLocalDate(value);
        }

        //is it a number, if so what type???

        //yada return the value
        return value;
    }

    public static Date getDate(@NonNull String subject) {
        Date localDate = null;
        try {
            localDate = DateUtil.parseDateString(subject);
        }
        catch (Exception e) {};
        return localDate;
    }

    public static LocalDate getLocalDate(@NonNull String subject) {
        LocalDate localDate = null;
        try {
            localDate = DateUtil.toLocalDate(subject);
        }
        catch (Exception e) {};
        return localDate;
    }

    public static Integer toInt(@NonNull String subject) {
        try {
            int multiply = 1;
            if(subject.startsWith("-")) multiply = -1;
            return Integer.parseInt(onlyDigits(subject)) * multiply;
        }
        catch (Exception e) {}

        return null;

    }

    public static Double toDouble(@NonNull String subject) {
        double multiply = 1F;
        if(subject.startsWith("-")) multiply = -1F;
        try {
            return Double.parseDouble(onlyDigits(subject)) * multiply;
        }
        catch (Exception e) {}
        return null;
    }

    public static String onlyDigits(String toClean) {
        return toClean.replaceAll("[^\\d.]+", "");
    }


}
