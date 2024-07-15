package com.example.imageboard.thread;

import com.example.imageboard.thread.dto.ThreadDto;
import com.example.imageboard.thread.exception.InvalidThreadCredentialsException;
import com.example.imageboard.thread.exception.ThreadNotFoundException;
import com.example.imageboard.thread.mapper.ThreadMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final ThreadMapper threadMapper;
    private final PasswordEncoder passwordEncoder;

    public ThreadService(ThreadRepository threadRepository, ThreadMapper threadMapper, PasswordEncoder passwordEncoder) {
        this.threadRepository = threadRepository;
        this.threadMapper = threadMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ThreadDto> getAll() {
        return threadRepository.findAll()
                .stream()
                .map(threadMapper::toDto)
                .collect(Collectors.toList());
    }

    public ThreadDto get(Long id) {
        return threadRepository.findById(id)
                .map(threadMapper::toDto)
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }

    public ThreadDto create(ThreadDto threadDto) {
        threadDto.setPassword(passwordEncoder.encode(threadDto.getPassword()));
        ForumThread thread = threadMapper.toEntity(threadDto);
        return threadMapper.toDto(threadRepository.save(thread));
    }

    public ThreadDto update(Long id, ThreadDto threadDto, String password) {
        return threadRepository.findById(id)
                .map(existingThread -> {
                    // Check if the existing thread has a password AND if a password was provided
                    if (existingThread.getPassword() != null && password != null) {
                        if (!passwordEncoder.matches(password, existingThread.getPassword())) {
                            throw new InvalidThreadCredentialsException();
                        }
                    }

                    // Update thread details
                    existingThread.setTitle(threadDto.getTitle());
                    existingThread.setContent(threadDto.getContent());
                    existingThread.setUrl(threadDto.getUrl());

                    // Update password only if provided in the dto AND not empty
                    if (threadDto.getPassword() != null && !threadDto.getPassword().isEmpty()) {
                        existingThread.setPassword(passwordEncoder.encode(threadDto.getPassword()));
                    }

                    return threadMapper.toDto(threadRepository.save(existingThread));
                })
                .orElseThrow(() -> new ThreadNotFoundException(id));
    }


    public void delete(Long id, String password) {
        threadRepository.findById(id)
                .ifPresentOrElse(thread -> {
                    if (password == null || thread.getPassword() == null || passwordEncoder.matches(password, thread.getPassword())) {
                        threadRepository.delete(thread);
                    } else {
                        throw new InvalidThreadCredentialsException();
                    }
                }, () -> {
                    throw new ThreadNotFoundException(id);
                });
    }
}





