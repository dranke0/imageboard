package com.example.imageboard.forumThread;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // For Spring integration
public interface ForumThreadMapper {
    ForumThreadDto toDto(ForumThread thread);
    ForumThread toEntity(ForumThreadDto threadDto);

    List<ForumThreadDto> toDtoList(List<ForumThread> threads);
    List<ForumThread> toEntityList(List<ForumThreadDto> threadDtos);
}
