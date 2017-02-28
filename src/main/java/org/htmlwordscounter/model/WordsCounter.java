package org.htmlwordscounter.model;

import org.htmlwordscounter.entities.Tag;
import org.htmlwordscounter.model.parser.Parser;
import org.htmlwordscounter.model.readers.ContentReader;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by MichaelMAC on 27.02.17.
 */
public class WordsCounter {

    private Map<String, Integer> wordsCount = new TreeMap<>();

    List<String> ignoredTags = new ArrayList<>(Arrays.asList("style", "script", "image", "object"));

    public void execute(ContentReader contentReader) throws IOException {
        Parser parser = new Parser();
        parser.setReader(contentReader.getReader());
        Tag body = getBodyTag(parser.getHTMLTag());
        countWords(body);
    }

    private void countWords(Tag tag){
        if(!ignoredTags.contains(tag.getName())){
            if(tag.getContent() != null)
                splitAndAdd(tag.getContent());
            for(Tag child : tag.getChildTags())
                countWords(child);
        }
    }

    private void splitAndAdd(String string){
        String withoutDuplicateSpaces = string.replaceAll("\\s+", " ");
        String[] result = withoutDuplicateSpaces.split("[\\s.,;—]");
        for(String s : result){
            if (s.matches("\\w+"))
                addToMap(s);
        }
    }

    private void addToMap(String word){
        if(wordsCount.containsKey(word))
            wordsCount.put(word, wordsCount.get(word) + 1);
        else
            wordsCount.put(word, 1);
    }

    private Tag getBodyTag(Tag html){
        for(Tag children : html.getChildTags()){
            if(children.getName().equals("body"))
                return children;
        }
        return null;
    }

    public Map<String, Integer> getWordsCount() {
        return wordsCount;
    }

}
