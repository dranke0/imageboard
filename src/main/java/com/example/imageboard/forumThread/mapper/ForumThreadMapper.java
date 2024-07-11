package com.example.imageboard.forumThread.mapper;

import com.example.imageboard.forumThread.ForumThread;
import com.example.imageboard.forumThread.dto.ForumThreadDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // For Spring integration
public interface ForumThreadMapper {
    ForumThreadDto toDto(ForumThread thread);
    ForumThread toEntity(ForumThreadDto threadDto);

    List<ForumThreadDto> toDtoList(List<ForumThread> threads);
    List<ForumThread> toEntityList(List<ForumThreadDto> threadDtos);
}
