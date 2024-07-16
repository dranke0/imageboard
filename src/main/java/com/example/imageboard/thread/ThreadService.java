package com.example.imageboard.thread;

import com.example.imageboard.thread.dto.ThreadDto;
import com.example.imageboard.thread.exception.ThreadNotFoundException;
import com.example.imageboard.thread.mapper.ThreadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final ThreadMapper threadMapper;

    public ThreadService(ThreadRepository threadRepository, ThreadMapper threadMapper) {
        this.threadRepository = threadRepository;
        this.threadMapper = threadMapper;
    }

    public List<ThreadDto> getAllThreads() {
        return threadRepository.findAll()
                .stream()
                .map(threadMapper::toDto)
                .collect(Collectors.toList());
    }

    public ThreadDto getThreadById(Long id) {
        return threadRepository.findById(id)
                .map(threadMapper::toDto)
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }

    public ThreadDto createThread(ThreadDto threadDto) {
        ForumThread thread = threadMapper.toEntity(threadDto);
        return threadMapper.toDto(threadRepository.save(thread));
    }

    public ThreadDto updateThread(Long id, ThreadDto threadDto) {
        return threadRepository.findById(id)
                .map(existingThread -> {
                    existingThread.setTitle(threadDto.getTitle());
                    existingThread.setContent(threadDto.getContent());
                    existingThread.setUrl(threadDto.getUrl());
                    return threadMapper.toDto(threadRepository.save(existingThread));
                })
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }

    public void deleteThread(Long id) {
        threadRepository.findById(id);
    }

    public List<ThreadDto> getThreadsByForumId(Long forumId) {
       List<ForumThread> threads = threadRepository.findByForumId(forumId);
       return threadMapper.toDtos(threads);
    }
}





