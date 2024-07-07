package com.example.imageboard.forum;

import com.example.imageboard.entity.EntityDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ForumDto extends EntityDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long threadCount; // Optional: include the number of forumThreads on the forum
}