package com.example.imageboard.comment.mapper;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.PublicUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "user", source = "user")
    CommentDto commentToCommentDto(Comment comment);

    List<CommentDto> commentsToCommentDtos(List<Comment> comments);

    PublicUserDto userToPublicUserDto(User user);

    // Modified method for mapping DTO to Comment
    @Mapping(target = "id", ignore = true)         // Ignore the ID as it should not be mapped
    @Mapping(target = "createdAt", ignore = true) // Ignore createdAt (as it's auto-generated)
    @Mapping(target = "updatedAt", ignore = true) // Ignore updatedAt (as it's auto-generated)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "user", ignore = true) // Ignore the user field during mapping
    @Mapping(target = "forumThread", ignore = true) // Ignore forumThread (as it should not be updated here)
    void updateCommentFromDto(CommentDto commentDto, @MappingTarget Comment comment);
}



