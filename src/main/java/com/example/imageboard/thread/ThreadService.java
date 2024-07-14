package com.example.imageboard.thread;

import com.example.imageboard.thread.dto.ThreadDto;
import com.example.imageboard.thread.exception.ThreadNotFoundException;
import com.example.imageboard.thread.mapper.ThreadMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final ThreadMapper threadMapper;

    public ThreadService(ThreadRepository threadRepository, ThreadMapper threadMapper) {
        this.threadRepository = threadRepository;
        this.threadMapper = threadMapper;
    }

    public Optional<ThreadDto> get(Long id) {
        return Optional.ofNullable(threadRepository.findById(id)
                .map(threadMapper::toDto)
                .orElseThrow(() -> new ThreadNotFoundException(id)));
    }

    public void create(ThreadDto threadDto) {
        ForumThread thread = threadMapper.toEntity(threadDto);
        threadRepository.save(thread);
    }

    public void update(Long id, ThreadDto threadDto) {
        threadRepository.findById(id)
                .orElseThrow(() -> new ThreadNotFoundException(id));
        Optional<ForumThread> thread = Optional.ofNullable(threadMapper.toEntity(threadDto));
        threadRepository.save(thread
                .orElseThrow(NullPointerException::new));
    }

    public void delete(Long id) throws ThreadNotFoundException{
        if(threadRepository.findById(id).isEmpty())
            throw new ThreadNotFoundException(id);
        threadRepository.deleteById(id);
    }
}




