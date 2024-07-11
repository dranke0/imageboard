package com.example.imageboard.forumThread.dto;

import com.example.imageboard.comment.CommentDto;
import com.example.imageboard.forumThread.ForumThreadStatus;
import com.example.imageboard.user.dto.AuthenticatedUserDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;  // Use LocalDateTime
import java.util.List;

@Data
@Builder
public class ForumThreadDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ForumThreadStatus status;
    private Long boardId;
    private AuthenticatedUserDto user; // Introduce a separate AuthorDto
    private List<CommentDto> posts;
}
