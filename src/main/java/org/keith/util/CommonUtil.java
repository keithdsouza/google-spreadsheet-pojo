package org.keith.util;

import lombok.NonNull;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
public class CommonUtil {
    public static Map<String, Integer> getHeader(@NonNull List<Object> headerRow) throws Exception{
        //do we need to throw exception or just constitute an error ??? change it if you want
        if(headerRow.size() <= 0) {
            throw new Exception("Data is incomplete, cannot create");
        }

        return IntStream.range(0, headerRow.size())
                .mapToObj(i -> i).collect(Collectors.toMap(i -> headerRow.get(i).toString(), i -> i));
    }

    public static Integer getStartRow(String subject) {
        String strPattern = "[a-zA-Z]+(\\d+):(.*)$";
        Pattern pattern = Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(subject);
        String result = null;
        if(matcher.find()) {
            result = matcher.group(1);
        }
        return Integer.parseInt(result);
    }
}
