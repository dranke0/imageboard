package com.example.imageboard.forum;

import com.example.imageboard.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import com.example.imageboard.forumThread.ForumThread;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = "forumThreads") // Exclude forumThreads to avoid recursion
public class Forum extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ForumThread> forumThreads;
}
