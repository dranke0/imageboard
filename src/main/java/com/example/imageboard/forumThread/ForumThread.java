package com.example.imageboard.forumThread;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.forum.Forum;
import com.example.imageboard.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "forum_threads") // Explicitly set the table name to "forum_threads"
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // Use only the 'id' for equality comparison
@NamedEntityGraph(name = "ForumThread.comments", attributeNodes = @NamedAttributeNode("comments"))
public class ForumThread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Typically preferred for auto-incrementing IDs
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Builder.Default // Set the default status to OPEN when using the builder
    private ForumThreadStatus status = ForumThreadStatus.OPEN;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id", nullable = false) // Renamed to "forum_id" for clarity
    private Forum forum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Renamed to "user" for clarity

    @OneToMany(mappedBy = "forumThread", cascade = CascadeType.ALL, orphanRemoval = true) // Fix mappedBy value
    @Builder.Default
    private List<Comment> comments = new ArrayList<>(); // Initialize the list for better handling

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
