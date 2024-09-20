package com.codility.tasks.hibernate.crud.solution;

import java.util.List;


public class ArticleDTO {
    private String title;
    private String content;
    private List<String> tags;


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
}
