package com.example.imageboard.comment.dto;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.thread.ForumThread;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto {
    private String content;
    private String imageUrl;
    private ForumThread thread;

    public CommentDto() {
    }

    public CommentDto(String content, String imageUrl, ForumThread thread) {
        this.content = content;
        this.imageUrl = imageUrl;
        this.thread = thread;
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

    public ForumThread getThread() {
        return thread;
    }

    public void setThread(ForumThread thread) {
        this.thread = thread;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "content='" + content + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", thread=" + thread +
                '}';
    }

}
