package com.example.imageboard.forum;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
public class ForumDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long threadCount; // Optional: include the number of forumThreads on the forum
}