package com.example.imageboard.thread.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.ForumRepository;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.prototype.Mapper;
import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.dto.ThreadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ThreadMapper implements Mapper<ThreadDto, ForumThread> {

    private final ForumRepository forumRepository;

    @Override
    public ThreadDto toDto(ForumThread thread) {
        if (thread == null) {
            return null;
        }
        return ThreadDto.builder()
                .id(thread.getId())
                .title(thread.getTitle())
                .forumId(thread.getForum().getId())
                .content(thread.getContent())
                .url(thread.getUrl())
                .build();
    }

    @Override
    public ForumThread toEntity(ThreadDto threadDto) {
        if (threadDto == null) {
            return null;
        }

        Forum forum = forumRepository.findById(threadDto.getForumId())
                .orElseThrow(() -> new ForumNotFoundException(threadDto.getForumId()));

        ForumThread thread = ForumThread.builder()
                .id(threadDto.getId())
                .title(threadDto.getTitle())
                .forum(forum)
                .content(threadDto.getContent())
                .url(threadDto.getUrl())
                .build();
                forum.getThreads().add(thread)
                return thread;
    }

    @Override
    public List<ThreadDto> toDto(List<ForumThread> threads) {
        return threads.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ForumThread> toEntity(List<ThreadDto> threadDtos) {
        return threadDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
