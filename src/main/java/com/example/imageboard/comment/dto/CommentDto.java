package com.example.imageboard.comment.dto;

public class CommentDto {

    private Long id;
    private Long threadId;
    private String content;

    public CommentDto() {
    }

    public CommentDto(Long id, Long threadId, String content) {
        this.id = id;
        this.threadId = threadId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", threadId=" + threadId +
                ", content='" + content + '\'' +
                '}';
    }
}

