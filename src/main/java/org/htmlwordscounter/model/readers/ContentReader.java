package org.htmlwordscounter.model.readers;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by MichaelMAC on 28.02.17.
 */
public interface ContentReader {
    BufferedReader getReader() throws IOException;
    void setPath(String path);
}
