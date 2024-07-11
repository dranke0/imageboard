package com.example.imageboard.forum.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForumDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long threadCount; // Store the pre-calculated thread count

    // Constructor updated to accept Long threadCount
    public ForumDto(Long id, String name, String description, Long threadCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.threadCount = threadCount;
    }
}

