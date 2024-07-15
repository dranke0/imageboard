package com.example.imageboard.thread;

import com.example.imageboard.thread.dto.ThreadDto;
import com.example.imageboard.thread.exception.ThreadNotFoundException;
import com.example.imageboard.thread.mapper.ThreadMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final ThreadMapper threadMapper;
    private final PasswordEncoder passwordEncoder;

    public ThreadService(ThreadRepository threadRepository, ThreadMapper threadMapper, PasswordEncoder passwordEncoder) {
        this.threadRepository = threadRepository;
        this.threadMapper = threadMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public ThreadDto get(Long id) {
        return threadRepository.findById(id)
                .map(threadMapper::toDto)
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }

    public ThreadDto create(ThreadDto threadDto) {
        if (threadDto.getPassword() != null && !threadDto.getPassword().isEmpty()) {
            threadDto.setPassword(passwordEncoder.encode(threadDto.getPassword()));
        }
        ForumThread thread = threadMapper.toEntity(threadDto);
        return threadMapper.toDto(threadRepository.save(thread));
    }

    public ThreadDto update(Long id, ThreadDto threadDto, String password) {
        return threadRepository.findById(id)
                .filter(existingThread -> passwordEncoder.matches(password, existingThread.getPassword()))
                .map(existingThread -> {
                    existingThread.setTitle(threadDto.getTitle());
                    existingThread.setContent(threadDto.getContent());
                    existingThread.setUrl(threadDto.getUrl());
                    if (threadDto.getPassword() != null && !threadDto.getPassword().isEmpty()) {
                        existingThread.setPassword(passwordEncoder.encode(threadDto.getPassword()));
                    }
                    return threadMapper.toDto(threadRepository.save(existingThread));
                })
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }
    public void delete(Long id, String password) {
        ForumThread thread = threadRepository.findById(id)
                .orElseThrow(() -> new ThreadNotFoundException(id));
        if (!passwordEncoder.matches(password, thread.getPassword())) {
            throw new RuntimeException("Password does not match");
        }
        threadRepository.delete(thread);
    }
}




