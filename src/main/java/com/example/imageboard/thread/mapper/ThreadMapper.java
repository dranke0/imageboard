package com.example.imageboard.thread.mapper;

import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.dto.ThreadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ThreadMapper {
    @Mapping(target = "forumId", expression = "java(thread.getForum() != null ? thread.getForum().getId() : null)")
    ThreadDto toDto(ForumThread thread);
    @Mapping(target = "forum", ignore = true)
    @Mapping(target= "comments", ignore = true)
    ForumThread toEntity(ThreadDto threadDto);
    List<ThreadDto>toDto(List<ForumThread> threads);
    List<ForumThread>toEntity(List<ThreadDto> threads);
}
