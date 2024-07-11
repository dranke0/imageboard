package com.example.imageboard.forum.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.dto.ForumDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ForumMapper {

    @Mapping(target = "threadCount", expression = "java(forum.getForumThreads().size())")
    ForumDto toForumDto(Forum forum);

    Forum toForum(ForumDto forumDto);

    // Method to update an existing Forum entity from a ForumDto
    @Mapping(target = "id", ignore = true)         // Don't map the ID, as it's already set in the existing entity
    @Mapping(target = "createdAt", ignore = true) // Don't update createdAt
    @Mapping(target = "updatedAt", ignore = true) // Don't update updatedAt (it'll be handled by @UpdateTimestamp)
    @Mapping(target = "forumThreads", ignore = true) // Don't update forumThreads
    void updateForumFromDto(ForumDto forumDto, @MappingTarget Forum forum);
}
