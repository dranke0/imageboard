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

    @Mapping(target = "user", source = "user") // Map User entity to PublicUserDto
    CommentDto commentToCommentDto(Comment comment);

    List<CommentDto> commentsToCommentDtos(List<Comment> comments);

    PublicUserDto userToPublicUserDto(User user);

    // Added method for mapping DTO to Comment
    @Mapping(target = "user", ignore = true) // Ignore the user field during mapping
    Comment commentDtoToComment(CommentDto commentDto);

    @Mapping(target = "user", ignore = true) // Ignore the user field during mapping
    @Mapping(target = "forumThread", ignore = true) // Ignore forumThread
    void updateCommentFromDto(CommentDto commentDto, @MappingTarget Comment comment);
}


