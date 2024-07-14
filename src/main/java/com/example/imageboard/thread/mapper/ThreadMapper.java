package com.example.imageboard.thread.mapper;

import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.dto.ThreadDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ThreadMapper {

    public ThreadDto toDto(ForumThread forumThread) {
        return new ThreadDto(
                forumThread.getTitle(),
                forumThread.getForumId(),
                forumThread.getName(),
                forumThread.getContent(),
                forumThread.getUrl()
        );
    }

    public ForumThread toEntity(ThreadDto threadDto) {
    return new ForumThread(
            threadDto.getTitle(),
            threadDto.getForumId(),
            threadDto.getName(),
            null,
            new ArrayList<>(),
            threadDto.getContent(),
            threadDto.getUrl()
    );
    }
}


