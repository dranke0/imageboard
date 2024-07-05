package com.example.imageboard.forumThread;

import com.example.imageboard.comment.CommentDto;
import com.example.imageboard.enums.ForumThreadStatus;
import com.example.imageboard.user.UserDto;
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
    private UserDto userDto; // Introduce a separate AuthorDto
    private List<CommentDto> posts;
}
