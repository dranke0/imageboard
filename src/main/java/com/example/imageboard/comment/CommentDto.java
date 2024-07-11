package com.example.imageboard.comment;

import com.example.imageboard.user.dto.PublicUserDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private Long id;
    private String content;
    private String imageUrl;
    private PublicUserDto user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long threadId;    // Include the ID of the forumThread the comment belongs to
}
