package com.example.imageboard.forumThread;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.forum.Forum;
import com.example.imageboard.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "forum_threads") // Explicitly set the table name to "forum_threads"
public class ForumThread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Typically preferred for auto-incrementing IDs
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private ForumThreadStatus status = ForumThreadStatus.OPEN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id", nullable = false) // Renamed to "forum_id" for clarity
    private Forum forum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "forumThread", cascade = CascadeType.ALL, orphanRemoval = true) // Fix mappedBy value
    private List<Comment> comments = new ArrayList<>(); // Initialize the list for better handling

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ForumThread() {
    }

    public ForumThread(Long id, String title, ForumThreadStatus status, Forum forum, User user, List<Comment> comments, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.forum = forum;
        this.user = user;
        this.comments = comments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && status == that.status && Objects.equals(forum, that.forum) && Objects.equals(user, that.user) && Objects.equals(comments, that.comments) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, status, forum, user, comments, createdAt);
    }

    @Override
    public String toString() {
        return "ForumThread{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", forum=" + forum +
                ", user=" + user +
                ", comments=" + comments +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
