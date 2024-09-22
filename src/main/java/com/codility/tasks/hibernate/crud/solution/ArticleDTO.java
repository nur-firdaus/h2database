package com.codility.tasks.hibernate.crud.solution;

import java.math.BigInteger;
import java.util.List;


public class ArticleDTO {
    private String title;
    private String content;
    private List<String> tags;
    private String stringTags;
    private Long id;

    public void setId(Long id){this.id=id;}

    public Long getId(){return this.id;}

    public String getStringTags() {
        return stringTags;
    }

    public void setStringTags(String title) {
        this.stringTags = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tag) {
        this.tags = tag;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tags=" + tags +
                ", stringTags='" + stringTags + '\'' +
                '}';
    }
}
