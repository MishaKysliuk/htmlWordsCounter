package org.htmlwordscounter.model.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by MichaelMAC on 28.02.17.
 */
public class FileContentReader implements ContentReader {

    private String path;

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new FileReader(new File(path)));
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }
}
