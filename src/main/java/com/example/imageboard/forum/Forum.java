package com.example.imageboard.forum;

import com.example.imageboard.forumThread.ForumThread;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "forums") // Explicitly set the table name to "forums"
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // Use only the 'id' for equality comparison
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Typically preferred for auto-incrementing IDs
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default // Initialize the list when using the builder
    private List<ForumThread> forumThreads = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

