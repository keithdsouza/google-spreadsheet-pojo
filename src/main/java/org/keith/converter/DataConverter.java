package org.keith.converter;

import org.apache.commons.beanutils.ConvertUtilsBean;

/**
 * You can handle all data types here, for example date or localdate as well as other custom
 * objects
 *
 * @author Keith Dsouza
 * Created on 3/28/18.
 */
public class DataConverter extends ConvertUtilsBean {
    @Override
    public Object convert(String value, Class clazz) {
        if (clazz.isEnum()){
            return Enum.valueOf(clazz, value.replaceAll(" ", "_").replaceAll("/", "_").toUpperCase());
        }
        else{
            return super.convert(value, clazz);
        }
    }
}
