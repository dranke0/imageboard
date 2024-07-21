package com.example.imageboard.comment.mapper;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.comment.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CommentMapper {
    //@Mapping(source = "threadId", target = "threadId")
    //@Mapping(target = "content", source = "content")
    CommentDto toDto(Comment comment);

    /*@Mapping(target = "thread", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)*/
    Comment toEntity(CommentDto commentDto);
    List<Comment> toEntity(List<CommentDto> commentDtos);
    List<CommentDto>toDto(List<Comment> comments);
}

