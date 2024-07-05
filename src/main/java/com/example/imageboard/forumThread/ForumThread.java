package com.example.imageboard.forumThread;

import com.example.imageboard.base.BaseEntity;
import com.example.imageboard.comment.Comment;
import com.example.imageboard.forum.Forum;

import jakarta.persistence.*;
import lombok.*;
import com.example.imageboard.user.User;

import java.util.List;

@Entity
@Getter @Setter @Builder // Lombok annotations
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = {"createdAt", "updatedAt", "comments"})
@NamedEntityGraph(name = "Thread.posts", attributeNodes = @NamedAttributeNode("comments"))
public class ForumThread extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private ForumThreadStatus status = ForumThreadStatus.OPEN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Forum forum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
