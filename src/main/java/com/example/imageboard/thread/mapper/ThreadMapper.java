package com.example.imageboard.thread.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.ForumRepository;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.dto.ThreadDto;
import org.springframework.stereotype.Component;

@Component
public class ThreadMapper {

    private final ForumRepository forumRepository;

    public ThreadMapper(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    public ThreadDto toDto(ForumThread thread) {
        if (thread == null) {
            return null;
        }
        return new ThreadDto(
                thread.getId(),
                thread.getTitle(),
                thread.getForum().getId(),
                thread.getName(),
                thread.getContent(),
                thread.getUrl(),
                thread.getPassword()
        );
    }

    public ForumThread toEntity(ThreadDto threadDto) {
        if (threadDto == null) {
            return null;
        }

        // Fetch Forum entity
        Forum forum = forumRepository.findById(threadDto.getForumId())
                .orElseThrow(() -> new ForumNotFoundException(threadDto.getForumId()));

        ForumThread thread = new ForumThread();
        thread.setId(threadDto.getId());
        thread.setTitle(threadDto.getTitle());
        thread.setForum(forum);
        thread.setName(threadDto.getName());
        thread.setContent(threadDto.getContent());
        thread.setUrl(threadDto.getUrl());
        forum.getThreads().add(thread);
        thread.setPassword(threadDto.getPassword());
        return thread;
    }
}


