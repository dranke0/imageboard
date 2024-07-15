package com.example.imageboard.thread;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.forum.Forum;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id", referencedColumnName = "id")
    private Forum forum;

    private String name;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public ForumThread(String title, Forum forum, String name, List<Comment> comments, String content, String url) {
        this.title = title;
        this.forum = forum;
        this.name = name;
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

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {

        if (this.forum != null) {
            this.forum.getThreads().remove(this);
        }
        this.forum = forum;
        if (forum != null) {
            forum.getThreads().add(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this.name.isEmpty())
            this.name = "Anonymous";
        else this.name = name;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForumThread that = (ForumThread) o;
        return Objects.equals(id, that.id) && Objects.equals(forum.getId(), that.forum.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, forum.getId());
    }

    @Override
    public String toString() {
        return "ForumThread{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", forum=" + forum +
                ", name='" + name + '\'' +
                ", comments=" + comments +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}


