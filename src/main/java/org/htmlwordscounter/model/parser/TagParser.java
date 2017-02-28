package org.htmlwordscounter.model.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MichaelMAC on 27.02.17.
 */
public class TagParser {

    private static final String PATTERN_FOR_TAG_NAME = "</?(?<tagName>.*?)(>|\\s|/)";


    public static  String getTagName(String tag){
        Pattern pattern = Pattern.compile(PATTERN_FOR_TAG_NAME);
        Matcher matcher = pattern.matcher(tag);
        if(!matcher.find())
            throw new IllegalArgumentException("Incorrect tag!");
        return matcher.group("tagName");
    }
}
