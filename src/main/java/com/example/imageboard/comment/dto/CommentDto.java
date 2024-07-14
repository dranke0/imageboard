package com.example.imageboard.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {
    private String content;
    private String imageUrl;
    private Long forumThreadId;

    public CommentDto() {
    }

    public CommentDto(String content, String imageUrl, Long forumThreadId) {
        this.content = content;
        this.imageUrl = imageUrl;
        this.forumThreadId = forumThreadId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getForumThreadId() {
        return forumThreadId;
    }

    public void setForumThreadId(Long forumThreadId) {
        this.forumThreadId = forumThreadId;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", forumThreadId=" + forumThreadId +
                '}';
    }
}
