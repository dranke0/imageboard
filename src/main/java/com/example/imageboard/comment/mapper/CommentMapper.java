package com.example.imageboard.comment.mapper;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.comment.dto.CommentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    public CommentDto toDto(Comment comment);
    public Comment toEntity(CommentDto commentDto);
    public List<Comment> toEntity(List<CommentDto> commentDtos);
    public List<CommentDto>toDto(List<Comment> comments);
}

