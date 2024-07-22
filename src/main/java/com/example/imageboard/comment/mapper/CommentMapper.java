package com.example.imageboard.comment.mapper;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.user.User;
import com.example.imageboard.user.UserRepository;
import com.example.imageboard.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CommentMapper {

    private final UserRepository userRepository;

    public CommentDto toDto(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUser().getId())
                .threadId(comment.getThread().getId())
                .build();
    }
    public Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        User user = userRepository.findById(commentDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(commentDto.getUserId()));
        return Comment.builder()
                .id(commentDto.getId())
                .user(user)
                .content(commentDto.getContent())
                .build();
    }
    public List<Comment> toEntity(List<CommentDto> commentDtos) {
        if (commentDtos == null) {
            return null;
        }
        return commentDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
    public List<CommentDto>toDto(List<Comment> comments) {
        if (comments == null) {
            return null;
        }
        return comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

