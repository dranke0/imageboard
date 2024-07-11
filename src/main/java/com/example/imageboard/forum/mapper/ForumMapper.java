package com.example.imageboard.forum.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.dto.ForumDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring") // For Spring integration
public interface ForumMapper {

    @Mapping(target = "threadCount", expression = "java(forum.getForumThreads().size())")
    ForumDto forumToForumDto(Forum forum);

    List<ForumDto> forumsToForumDtos(List<Forum> forums); // Add this method

    Forum forumDtoToForum(ForumDto forumDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "forumThreads", ignore = true)
    void updateForumFromDto(ForumDto forumDto, @MappingTarget Forum forum);
}


