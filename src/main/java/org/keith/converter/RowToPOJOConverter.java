package org.keith.converter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.keith.model.Sheet;
import org.keith.model.Spreadsheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.keith.util.CommonUtil.getStartRow;

/**
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
@Slf4j
public class RowToPOJOConverter {
    /**
     * @param clazz    The class we want to create from the data
     * @param rows     The rows we read from Google spreadsheet API
     * @param headers  The headers from this spreadsheet, read from the first row by default
     * @param mappings The mappings from the header to the POJO we want to recreate, e.g. User Name to "username"
     * @param <T>      - The clazz we want to create from any row
     * @return
     */
    public static <T> List<T> convert(Class<T> clazz, Spreadsheet spreadsheet, Sheet sheet,
                                      List<List<Object>> rows, Map<String, Integer> headers,
                                      Map<String, String> mappings, Map<String, Object> extraClazzData) {

        AtomicInteger counter = new AtomicInteger(getStartRow(sheet.getRange()));
        Map<String, Object> sharedClazzData = new HashMap<>();
        //add sheet metadata to the object
        sharedClazzData.put("spreadsheet", spreadsheet);
        sharedClazzData.put("sheet", sheet);
        sharedClazzData.put("mapping", mappings);
        sharedClazzData.put("header", headers);
        if(extraClazzData != null) {
            sharedClazzData.putAll(extraClazzData);
        }

        return rows.stream().map(r -> {
            Map<String, Object> localClazzData = new HashMap<>(sharedClazzData);
            //add the row number to the pojo, this is useful when we want to update specific rows
            localClazzData.put("row", counter.incrementAndGet());

            //go through all the mappings and look at the headers to see if
            //we have data that matches the field to create a property -> value mapping
            mappings.forEach((key, field) -> {
                Integer index = headers.get(key);
                if (index != null) {
                    Object value = r.size() > index ? r.get(index) : null;
                    localClazzData.put(field, value);
                } else {
                    log.trace("Could not find mapping for {}", key);
                }
            });

            //Create the POJO and return it
            try {
                T t = clazz.newInstance();
                BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new DataConverter());
                beanUtilsBean.populate(t, localClazzData);
                return t;
            } catch (Exception e) {
                log.error("Could not convert row to object");
                log.error("Object failed conversion {}", localClazzData);
                log.debug("Exception", e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
