package com.example.imageboard.forumThread;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.ForumRepository;
import com.example.imageboard.comment.CommentDto;
import com.example.imageboard.forumThread.dto.ForumThreadDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class ForumThreadService {

    private final ForumThreadRepository forumThreadRepository;
    private final ForumRepository forumRepository;

    public List<ForumThreadDto> getAllThreads() {
        List<ForumThread> forumThreads = forumThreadRepository.findAll();
        log.info("Fetched {} forumThreads", forumThreads.size());
        return forumThreads.stream()
                .map(this::convertToForumThreadDto)
                .collect(Collectors.toList());
    }

    public ForumThreadDto getThreadById(Long id) {
        return forumThreadRepository.findById(id)
            .map(this::convertToForumThreadDto) // Use helper method to convert with comments
            .orElseThrow(() -> new EntityNotFoundException("ForumThread", id.toString()));
    }

    @Transactional
    public ForumThreadDto createThread(@Valid ForumThreadDto forumThreadDto) {
        Forum forum = forumRepository.findById(forumThreadDto.getBoardId()) // Fetch associated forum
                .orElseThrow(() -> new ResourceNotFoundException("Forum", forumThreadDto.getBoardId().toString()));

        ForumThread forumThread = modelMapper.map(forumThreadDto, ForumThread.class);
        forumThread.setForum(forum); // Set the forum for the forumThread
        return modelMapper.map(forumThreadRepository.save(forumThread), ForumThreadDto.class);
}

    @Transactional
    public ForumThreadDto updateThread(Long id, @Valid ForumThreadDto updatedForumThreadDto) {
        ForumThread existingForumThread = forumThreadRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ForumThread", id.toString()));

        // Only update fields explicitly allowed by the DTO (avoid unintended changes)
        existingForumThread.setTitle(updatedForumThreadDto.getTitle());

        existingForumThread.getStatus();
        //existingForumThread.setIsLocked(updatedForumThreadDto.getIsLocked());

        return modelMapper.map(forumThreadRepository.save(existingForumThread), ForumThreadDto.class);
}
    @Transactional
    public void deleteThread(Long id) {
        if (!forumThreadRepository.existsById(id)) {
            throw new ResourceNotFoundException("ForumThread", id.toString());
        }
        forumThreadRepository.deleteById(id);
    }

    private long getTotalForumThreads() {
        return forumThreadRepository.count();
    }

    public List<ForumThreadDto> getForumThreadsByForumId(Long forumId) {
        List<ForumThread> forumThreads = forumThreadRepository.findByForumId(forumId);
        return forumThreads.stream()
                .map(forumThread -> {
                    ForumThreadDto forumThreadDto = modelMapper.map(forumThread, ForumThreadDto.class);
                    forumThreadDto.setPosts(forumThread.getComments().stream() // Map comments if needed
                            .map(post -> modelMapper.map(post, CommentDto.class))
                            .collect(Collectors.toList()));
                    return forumThreadDto;
                })
                .collect(Collectors.toList());
    }

    public Page<ForumThread> getMostActiveForumThreads(LocalDateTime startDate, Pageable pageable) {
        return forumThreadRepository.findMostActiveForumThreads(startDate, pageable);
    }

    // Helper method to convert ForumThread to ForumThreadDto with Posts
    private ForumThreadDto convertToForumThreadDto(ForumThread forumThread) {
        ForumThreadDto forumThreadDto = modelMapper.map(forumThread, ForumThreadDto.class);
        forumThreadDto.setPosts(forumThread.getComments().stream()
                .map(post -> this::convertToForumThreadDto)
                .collect(Collectors.toList()));
        return forumThreadDto;
    }
}


