package com.example.imageboard.forum;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ForumDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long threadCount; // Optional: include the number of forumThreads on the forum
}