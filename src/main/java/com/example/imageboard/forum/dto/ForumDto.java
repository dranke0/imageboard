package com.example.imageboard.forum.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)  // Include only non-null fields in JSON serialization
public class ForumDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Long threadCount;

    public ForumDto(Long id, String name, String description, Long threadCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.threadCount = threadCount != null ? threadCount : 0L;
    }
}
