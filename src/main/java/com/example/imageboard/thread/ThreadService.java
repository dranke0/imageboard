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

    public List<ThreadDto> getAll() {
        return threadRepository.findAll()
                .stream()
                .map(threadMapper::toDto)
                .collect(Collectors.toList());
    }

    public ThreadDto getById(Long id) {
        return threadRepository.findById(id)
                .map(threadMapper::toDto)
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }

    public ThreadDto create(Long forumId, ThreadDto threadDto) {
        // Map DTO to entity and set the forumId
        ForumThread thread = threadMapper.toEntity(threadDto);
        thread.setForumId(forumId);
        return threadMapper.toDto(threadRepository.save(thread));
    }

    public ThreadDto update(Long forumId, Long threadId, ThreadDto threadDto) {
        return threadRepository.findById(threadId)
                .map(existingThread -> {
                    // Check if the existing thread belongs to the specified forum
                    if (!existingThread.getForumId().equals(forumId)) {
                        throw new IllegalArgumentException("Thread with id " + threadId + " does not belong to forum with id " + forumId);
                    }
                    // Update thread details
                    existingThread.setTitle(threadDto.getTitle());
                    existingThread.setContent(threadDto.getContent());
                    existingThread.setUrl(threadDto.getUrl());
                    return threadMapper.toDto(threadRepository.save(existingThread));
                })
                .orElseThrow(() -> new ThreadNotFoundException(threadId));
    }

    public void delete(Long forumId, Long threadId) {
        threadRepository.findById(threadId)
                .ifPresent(existingThread -> {
                    // Check if the existing thread belongs to the specified forum
                    if (!existingThread.getForumId().equals(forumId)) {
                        throw new IllegalArgumentException("Thread with id " + threadId + " does not belong to forum with id " + forumId);
                    }
                    threadRepository.delete(existingThread);
                });
    }

    public List<ThreadDto> getByForumId(Long forumId) {
        List<ForumThread> threads = threadRepository.findByForumId(forumId);
        return threadMapper.toDto(threads);
    }
}




