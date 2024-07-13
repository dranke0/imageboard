package com.example.imageboard.forum; // Adjust the package if needed

import com.example.imageboard.forum.validator.ForumValidator;
import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.dto.ThreadDto;
import com.example.imageboard.thread.mapper.ThreadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // Use @Transactional for all methods that modify data
public class ForumService {
    private final ForumRepository forumRepository;
    private final ThreadMapper threadMapper;
    private final ForumValidator forumValidator;

    public ForumService(ForumRepository forumRepository, ThreadMapper threadMapper, ForumValidator forumValidator) {
        this.forumRepository = forumRepository;
        this.threadMapper = threadMapper;
        this.forumValidator = forumValidator;
    }

    public List<ThreadDto> getAll() {
        List<ForumThread> forumThread = forumRepository.findAll();
        threadMapper.toDto()

    }
}



