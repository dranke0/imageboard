package com.example.imageboard.forumThread.dto;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.forum.Forum;
import com.example.imageboard.forumThread.ForumThreadStatus;
import com.example.imageboard.user.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForumThreadDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ForumThreadStatus status;
    private Forum forum;
    private User user ;
    private List<CommentDto> comments; // Use comments to keep consistent

    public ForumThreadDto() {
    }

    public ForumThreadDto(Long id, String title, LocalDateTime createdAt, LocalDateTime updatedAt, ForumThreadStatus status, Forum forum, User user, List<CommentDto> comments) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.forum = forum;
        this.user = user;
        this.comments = comments;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ForumThreadStatus getStatus() {
        return status;
    }

    public void setStatus(ForumThreadStatus status) {
        this.status = status;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumThreadDto that = (ForumThreadDto) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(createdAt, that.createdAt) && status == that.status && Objects.equals(forum, that.forum) && Objects.equals(user, that.user) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createdAt, status, forum, user, comments);
    }

    @Override
    public String toString() {
        return "ForumThreadDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", status=" + status +
                ", forum=" + forum +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}

