package com.example.imageboard.thread;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.forum.Forum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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

    public void setForum(Forum forum) {
        if (this.forum != null) {
            this.forum.getThreads().remove(this);
        }
        this.forum = forum;
        if (forum != null) {
            forum.getThreads().add(this);
        }
    }

    public Long getForumId() {
        return forum != null ? forum.getId() : null;
    }

    public void setForumId(Long forumId) {
        if (forum == null) {
            forum = new Forum();
        }
        forum.setId(forumId);
    }

    public void setComments(List<Comment> comments) {
        if (comments != null) {
            this.comments.clear();
            this.comments.addAll(comments);
            comments.forEach(comment -> comment.setThread(this));
        } else {
            this.comments.clear();
        }
    }
}



