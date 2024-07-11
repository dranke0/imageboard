package com.example.imageboard.comment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String content;
    private String imageUrl;
    private UserDto userDto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long threadId;    // Include the ID of the forumThread the comment belongs to
}
