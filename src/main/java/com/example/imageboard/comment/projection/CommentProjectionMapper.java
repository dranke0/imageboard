package com.example.imageboard.comment.projection;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.PublicUserDto;
import com.example.imageboard.user.mapper.PublicUserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = PublicUserMapper.class)
public abstract class CommentProjectionMapper {

    @Autowired
    protected PublicUserMapper publicUserMapper;

    @Mapping(target = "author", source = ".", qualifiedByName = "usernameToPublicUserDto")
    public abstract CommentDto commentProjectionToCommentDto(CommentProjection commentProjection);

    @Named("usernameToPublicUserDto")
    public PublicUserDto usernameToPublicUserDto(CommentProjection commentProjection) {
        return publicUserMapper.toPublicUserDto(
                User.builder().username(commentProjection.getAuthorUsername()).build()
        );
    }
}



