package com.example.imageboard.forumThread.dto;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forumThread.ForumThreadStatus;
import com.example.imageboard.user.dto.PublicUserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForumThreadDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ForumThreadStatus status;
    private ForumDto forum; // Use ForumDto to represent the forum
    private PublicUserDto author; // Use PublicUserDto to represent the author
    private List<CommentDto> comments; // Use comments to keep consistent
}

