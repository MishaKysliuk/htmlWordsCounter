package org.htmlwordscounter.model.readers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by MichaelMAC on 28.02.17.
 */
public class URLContentReader implements ContentReader {

    private String path;

    @Override
    public BufferedReader getReader() throws IOException {
        URL url = new URL(path);
        return new BufferedReader(new InputStreamReader(url.openStream()));
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }
}
