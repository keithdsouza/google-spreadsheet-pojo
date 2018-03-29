package org.keith.model;

import java.util.List;

/**
 * Allows to filter out rows we don't want from the raw data
 *
 * example row -> row.get(0) != null or row.get(10) != null && row.get(10).toString().equalsIgnoreCase("something")
 *
 * @author Keith Dsouza
 * Created on 3/26/18.
 */
@FunctionalInterface
public interface RowFilter {
    boolean filter(List<Object> row);
}
