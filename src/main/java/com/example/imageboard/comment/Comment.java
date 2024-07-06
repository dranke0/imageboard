package com.example.imageboard.comment;

import com.example.imageboard.entity.EntityModel;
import jakarta.persistence.*;
import lombok.*;
import com.example.imageboard.user.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import com.example.imageboard.forumThread.ForumThread;

@Entity
@Getter @Setter @Builder // Lombok annotations
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = {"createdAt", "updatedAt"})
public class Comment extends EntityModel {

    @Column(nullable = false, length = 5000)
    @Length(max = 5000, message = "Content cannot exceed 5,000 characters")
    private String content;

    @URL(message = "Invalid image URL")
    @Column(length = 2048, nullable = true)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private CommentStatus status = CommentStatus.ACTIVE; // Default status

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id", nullable = false)
    private ForumThread forumThread;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}


