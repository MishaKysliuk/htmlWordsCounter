package org.htmlwordscounter.helpers;

/**
 * Created by MichaelMAC on 26.02.17.
 */
public class Validate {

    public static final String URL_PATTERN = "http://(www\\.)?.+\\..+";


    public static void matchesPattern(String string, String pattern){
        if(!string.matches(pattern)){
            throw new IllegalArgumentException("String does not match pattern");
        }
    }

}
