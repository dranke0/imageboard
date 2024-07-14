package com.example.imageboard.thread.dto;

import com.example.imageboard.forum.Forum;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThreadDto {
    private String title;
    private Forum forum;
    private String name;
    private String content;
    private String url;

    public ThreadDto() {
    }

    public ThreadDto(String title, Forum forum, String name, String content, String url) {
        this.title = title;
        this.forum = forum;
        this.name = name;
        this.content = content;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForumId(Forum forum) {
        this.forum = forum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "ThreadDto{" +
                ", title='" + title + '\'' +
                ", forum='" + forum + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

