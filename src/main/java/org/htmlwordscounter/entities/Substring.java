package org.htmlwordscounter.entities;

/**
 * Created by MichaelMAC on 26.02.17.
 */
public class Substring {
    private int index;
    private String string;

    public Substring(int index, String string) {
        this.index = index;
        this.string = string;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
