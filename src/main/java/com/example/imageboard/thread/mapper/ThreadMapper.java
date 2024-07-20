package com.example.imageboard.thread.mapper;

import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.dto.ThreadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ThreadMapper {
    ForumThread toEntity(ThreadDto threadDto);
    ThreadDto toDto(ForumThread thread);
    List<ThreadDto>toDto(List<ForumThread> threads);
    List<ForumThread>toEntity(List<ThreadDto> threads);
}
