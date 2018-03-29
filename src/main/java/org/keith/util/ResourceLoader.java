package org.keith.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Keith Dsouza
 * Created on 3/23/18.
 */
public class ResourceLoader {
    /**
     * Stricly here for IDE vs Jar compatibility
     **/
    public static String get(String fileName) throws IOException {
        File file = null;
        String resource = fileName;
        URL res = ResourceLoader.class.getClassLoader().getResource(resource);
        if (res.toString().startsWith("jar:")) {
            try {
                return IOUtils.toString(res.openStream(), "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            return FileUtils.readFileToString(new File(res.getFile()), "UTF-8");
        }

        throw new RuntimeException("Error: File " + file + " not found!");
    }
}
