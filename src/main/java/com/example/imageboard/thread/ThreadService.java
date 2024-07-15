package com.example.imageboard.thread;

import com.example.imageboard.thread.dto.ThreadDto;
import com.example.imageboard.thread.exception.ThreadNotFoundException;
import com.example.imageboard.thread.mapper.ThreadMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<ForumThread> threads = threadRepository.findAll();
        return threads.stream()
                .map(threadMapper::toDto)
                .toList();
    }

    public ThreadDto get(Long id) {
        return threadRepository.findById(id)
                .map(threadMapper::toDto)
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }

    public ThreadDto create(ThreadDto threadDto) {
        ForumThread thread = threadMapper.toEntity(threadDto);
        return threadMapper.toDto(threadRepository.save(thread));
    }

    public ThreadDto update(Long id, ThreadDto threadDto) {
        return threadRepository.findById(id)
                .map(existingThread -> {
                    existingThread.setTitle(threadDto.getTitle());
                    existingThread.setContent(threadDto.getContent());
                    existingThread.setUrl(threadDto.getUrl());
                    return threadMapper.toDto(threadRepository.save(existingThread));
                })
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }
    public void delete(Long id) {
        threadRepository.deleteById(id);
    }
}




