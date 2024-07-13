package com.example.imageboard.thread.mapper;

import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.dto.ThreadDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class ThreadMapper {

    public Optional<ThreadDto> toDto(ForumThread forumThread) {
        return Optional.of(new ThreadDto(
                forumThread.getId(),
                forumThread.getTitle(),
                forumThread.getForumId(),
                forumThread.getName(),
                forumThread.getContent(),
                forumThread.getUrl()
        ));
    }

    public Optional<ForumThread> toEntity(ThreadDto threadDto) {
    return Optional.of(new ForumThread(
            threadDto.getTitle(),
            threadDto.getForumId(),
            threadDto.getName(),
            null,
            new ArrayList<>(),
            threadDto.getContent(),
            threadDto.getUrl()
    ));
    }
}


