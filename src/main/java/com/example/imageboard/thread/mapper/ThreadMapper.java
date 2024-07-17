package com.example.imageboard.thread.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.ForumRepository;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.dto.ThreadDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
                thread.getContent(),
                thread.getUrl()
        );
    }

    public ForumThread toEntity(ThreadDto threadDto) {
        if (threadDto == null) {
            return null;
        }

        Forum forum = forumRepository.findById(threadDto.getForumId())
                .orElseThrow(() -> new ForumNotFoundException(threadDto.getForumId()));

        ForumThread thread = new ForumThread();
        thread.setId(threadDto.getId());
        thread.setTitle(threadDto.getTitle());
        thread.setForum(forum);
        thread.setContent(threadDto.getContent());
        thread.setUrl(threadDto.getUrl());
        forum.getThreads().add(thread);
        return thread;
    }

    public List<ThreadDto> toDtos(List<ForumThread> threads) {
        return threads
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ForumThread> toEntity(List<ThreadDto> threadDtos) {
        return threadDtos
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}


