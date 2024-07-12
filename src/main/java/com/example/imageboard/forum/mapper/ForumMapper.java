package com.example.imageboard.forum.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.dto.ForumDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ForumMapper {

    Forum forumToForumDto(Forum forum);

    List<ForumDto> forumsToForumDtos(List<Forum> forums);

    // Removed unnecessary mappings from forumDtoToForum
    Forum forumDtoToForum(ForumDto forumDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateForumFromDto(ForumDto forumDto, @MappingTarget Forum forum); // Removed unnecessary mappings
}




