package org.htmlwordscounter.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MichaelMAC on 26.02.17.
 */
public class Tag {

    private String name;

    private List<Tag> childTags = new ArrayList<Tag>();

    private String content;

    public Tag(){

    }
    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getChildTags() {
        return childTags;
    }

    public void setChildTags(List<Tag> childTags) {
        this.childTags = childTags;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
