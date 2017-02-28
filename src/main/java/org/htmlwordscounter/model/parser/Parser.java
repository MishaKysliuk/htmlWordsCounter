package org.htmlwordscounter.model.parser;

import org.htmlwordscounter.entities.Tag;
import org.htmlwordscounter.exceptions.NoClosingTagException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by MichaelMAC on 26.02.17.
 */
public class Parser {

    private static final String OPEN_BKT = "<";
    private static final String CLOSING_TAG_BKT = "</";
    private static final String OPEN_COMMENT = "<!--";
    private static final String CLOSE_BKT = ">";
    private static final String CLOSE_COMMENT = "-->";
    private static final String SELF_CLOSING_TAG_BKT = "/>";
    private static final String HTML_TAG = "<html";

    List<String> mayNotBeClosedTags = new ArrayList<>(Arrays.asList("br", "img", "meta", "link"));


    private BufferedReader reader;

    private String currentLine;
    private int cursor;


    public Tag getHTMLTag() throws IOException {
        Tag html = new Tag();
        html.setName("html");
        newLine(true);
        cursor = indexOf(HTML_TAG) + HTML_TAG.length();
        buildTree(html);
        return html;
    }

    private void skipComments() throws IOException {
        cursor = indexOf(CLOSE_COMMENT) + CLOSE_COMMENT.length();
    }

    private void buildTree(Tag parent) throws IOException {
        StringJoiner tagContent = new StringJoiner(" ");

        while(true) {
            int openTagBktIndex = indexOfOpenBkt(tagContent);
            cursor = openTagBktIndex;

            if(openTagBktIndex == currentLine.indexOf(OPEN_COMMENT)){
                skipComments();
                continue;
            }

            int closeTagBktIndex = indexOfCloseBkt();
            String stringBetweenBkts = currentLine.substring(cursor, closeTagBktIndex + CLOSE_BKT.length());
            String tagName = TagParser.getTagName(stringBetweenBkts);

            if(currentLine.indexOf(SELF_CLOSING_TAG_BKT, cursor) == (closeTagBktIndex - 1)){ //(closeTagBktIndex - 1) could be the index of '/>'
                    Tag child = new Tag(tagName);
                    parent.getChildTags().add(child);
                    cursor = closeTagBktIndex + CLOSE_BKT.length();
            }else if(currentLine.indexOf(CLOSING_TAG_BKT, cursor) == openTagBktIndex){
                if(tagName.equals(parent.getName())){
                    cursor = closeTagBktIndex + CLOSE_BKT.length();
                    parent.setContent(tagContent.toString());
                    return;
                }else {
                    if(mayNotBeClosedTags.contains(parent.getName()))
                        return;
                    throw new NoClosingTagException("Incorrect tag is closed!");
                }
            }else{
                if(mayNotBeClosedTags.contains(parent.getName()))
                    return;
                else{
                    Tag child = new Tag(tagName);
                    parent.getChildTags().add(child);
                    cursor = closeTagBktIndex + CLOSE_BKT.length();
                    buildTree(child);
                }
            }
        }
    }

    private void newLine(boolean refreshCursor) throws IOException {
        currentLine = reader.readLine().toLowerCase().trim();
        if (refreshCursor)
            cursor = 0;
    }

    private int indexOf(String toFind) throws IOException {
        int index = currentLine.indexOf(toFind, cursor);
        while (index == -1) {
            newLine(true);
            index = currentLine.indexOf(toFind, cursor);
        }
        return index;
    }

    /**
     * TagContent StringBuilder is used for saving content of the tag when searching the index of Open Bracket.
     * @param tagContent
     * @return
     * @throws IOException
     */
    private int indexOfOpenBkt(StringJoiner tagContent) throws IOException {
        int index = currentLine.indexOf(OPEN_BKT, cursor);
        while (index == -1) {
            tagContent.add(currentLine.substring(cursor));
            newLine(true);
            index = currentLine.indexOf(OPEN_BKT, cursor);
        }
        tagContent.add(currentLine.substring(cursor, index));
        return index;
    }

    /**
     * In order not to lose the content while reading the new line, the current line is not read again, but concatenate with the previous.
     * The saving of old lines is necessary when we have the tag that may not be closed.
     * @return index of the endString
     * @throws IOException
     */
    private int indexOfCloseBkt() throws IOException{
        int index = currentLine.indexOf(CLOSE_BKT, cursor);
        //joinerForCurrentLine is required in order not to lose previous line when we could not find endString in currentLine and go to the next
        StringJoiner joinerForCurrentLine = new StringJoiner(" ");
        joinerForCurrentLine.add(currentLine);
        while (index == -1) {
            newLine(false);
            joinerForCurrentLine.add(currentLine);
            currentLine = joinerForCurrentLine.toString();
            index = currentLine.indexOf(CLOSE_BKT, cursor);
        }
        return index;
    }


    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}
