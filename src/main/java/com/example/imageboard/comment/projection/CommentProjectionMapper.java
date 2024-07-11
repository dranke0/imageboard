package com.example.imageboard.comment.projection;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.PublicUserDto;
import com.example.imageboard.user.mapper.PublicUserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CommentProjectionMapper {

    @Mapping(target = "author", source = ".", qualifiedByName = "usernameToPublicUserDto")
    CommentDto commentProjectionToCommentDto(CommentProjection commentProjection, PublicUserMapper publicUserMapper);

    @Named("usernameToPublicUserDto")
    default PublicUserDto usernameToPublicUserDto(CommentProjection commentProjection, PublicUserMapper publicUserMapper) {
        return publicUserMapper.toPublicUserDto(
                User.builder().username(commentProjection.getAuthorUsername()).build()
        );
    }
}



