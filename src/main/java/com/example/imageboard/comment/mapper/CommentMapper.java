package com.example.imageboard.comment.mapper;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.user.mapper.PublicUserMapper; // Assuming you have this mapper
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = PublicUserMapper.class)
public interface CommentMapper {
    @Mapping(target = "author", source = "user")
    @Mapping(target = "threadId", source = "forumThread.id")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(target = "user", source = "author.id")
    @Mapping(target = "forumThread", ignore = true) // We'll handle this manually in the service
    Comment commentDtoToComment(CommentDto commentDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "forumThread", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateCommentFromCommentDto(CommentDto commentDto, @MappingTarget Comment comment);
}
