package com.example.imageboard.forum.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.dto.ForumDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ForumMapper {

    @Mapping(target = "threadCount", expression = "java(forum.getForumThreads().size())")
    @Mapping(target = "updatedAt", ignore = true)  // Ignore updatedAt during the mapping
    @Mapping(target = "forumThreads", ignore = true)  // Ignore forumThreads during the mapping
    @Mapping(target = "threadCount", expression = "java((long) forum.getForumThreads().size())")
    ForumDto forumToForumDto(Forum forum);

    List<ForumDto> forumsToForumDtos(List<Forum> forums);

    @Mapping(target = "updatedAt", ignore = true)  // Ignore updatedAt during the mapping
    @Mapping(target = "forumThreads", ignore = true)  // Ignore forumThreads during the mapping
    Forum forumDtoToForum(ForumDto forumDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "forumThreads", ignore = true)
    void updateForumFromDto(ForumDto forumDto, @MappingTarget Forum forum);
}



