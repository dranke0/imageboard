package com.example.imageboard.thread.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThreadDto {

    private Long id;
    private String title;
    private Long forumId;
    private String content;
    private String url;

    public ThreadDto() {
    }

    public ThreadDto(Long id, String title, Long forumId, String content, String url) {
        this.id = id;
        this.title = title;
        this.forumId = forumId;
        this.content = content;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getForumId() {
        return forumId;
    }

    public void setForumId(Long forumId) {
        this.forumId = forumId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThreadDto)) return false;
        ThreadDto threadDto = (ThreadDto) o;
        return Objects.equals(id, threadDto.id) &&
                Objects.equals(forumId, threadDto.forumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forumId);
    }

    @Override
    public String toString() {
        return "ThreadDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", forumId=" + forumId +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}


