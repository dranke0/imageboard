package com.example.imageboard.comment.dto;

import com.example.imageboard.user.dto.PublicUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)  // Exclude null fields from JSON serialization
public class CommentDto {
    private Long id;
    private String content;
    private String imageUrl;
    private PublicUserDto author;  // Rename to author for clarity (if applicable)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long threadId;
}
