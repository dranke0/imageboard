package com.example.imageboard.thread;

import com.example.imageboard.comment.Comment;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "threads")
public class ForumThread {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "forum_id")
    private Long forumId;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) default 'Anonymous'") // Set default value to "Anonymous
    private String name = "Anonymous";

    private String password;

    @OneToMany(mappedBy = "forumThread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    private String content;

    @Column
    private String url;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ForumThread() {
    }

    public ForumThread(String title, Long forumId, String name, String password, List<Comment> comments, String content, String url) {
        this.title = title;
        this.forumId = forumId;
        this.name = name;
        this.password = password;
        this.comments = comments;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumThread that = (ForumThread) o;
        return Objects.equals(id, that.id) && Objects.equals(forumId, that.forumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forumId);
    }

    @Override
    public String toString() {
        return "ForumThread{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", forumId=" + forumId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", comments=" + comments +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}


