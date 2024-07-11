package com.example.imageboard.forumThread;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.ForumRepository;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.forumThread.dto.ForumThreadDto;
import com.example.imageboard.forumThread.exception.ForumThreadNotFoundException;
import com.example.imageboard.forumThread.mapper.ForumThreadMapper;
import com.example.imageboard.comment.mapper.CommentMapper; // Added
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumThreadService {

    private final ForumThreadRepository forumThreadRepository;
    private final ForumRepository forumRepository;
    private final ForumThreadMapper forumThreadMapper;
    private final CommentMapper commentMapper;

    public Page<ForumThreadDto> getAllForumThreads(Pageable pageable) {
        Page<ForumThread> forumThreads = forumThreadRepository.findAll(pageable); // Pass Pageable to findAll()
        return forumThreads.map(forumThreadMapper::forumThreadToForumThreadDto);
    }

    public ForumThreadDto getForumThreadById(Long id) {
        ForumThread forumThread = forumThreadRepository.findById(id)
                .orElseThrow(() -> new ForumThreadNotFoundException(id));
        return forumThreadMapper.forumThreadToForumThreadDto(forumThread); // Use the mapper
    }

    @Transactional
    public ForumThreadDto createForumThread(@Valid ForumThreadDto forumThreadDto) {
        Forum forum = forumRepository.findById(forumThreadDto.getForum().getId())
                .orElseThrow(() -> new ForumNotFoundException(forumThreadDto.getForum().getId()));

        ForumThread forumThread = forumThreadMapper.toForumThread(forumThreadDto);
        forumThread.setForum(forum);
        return forumThreadMapper.forumThreadToForumThreadDto(forumThreadRepository.save(forumThread));
    }

    @Transactional
    public ForumThreadDto updateForumThread(Long id, @Valid ForumThreadDto updatedForumThreadDto) {
        ForumThread existingForumThread = forumThreadRepository.findById(id)
                .orElseThrow(() -> new ForumThreadNotFoundException(id));

        forumThreadMapper.updateForumThreadFromForumThreadDto(updatedForumThreadDto, existingForumThread);

        return forumThreadMapper.forumThreadToForumThreadDto(forumThreadRepository.save(existingForumThread));
    }

    @Transactional
    public void deleteForumThread(Long id) {
        forumThreadRepository.deleteById(id);
    }

    public long getTotalForumThreads() {
        return forumThreadRepository.count();
    }

    public Page<ForumThreadDto> getForumThreadsByForumId(Long forumId, Pageable pageable) {
        Page<ForumThread> forumThreads = forumThreadRepository.findByForumId(forumId, pageable);
        return forumThreads.map(forumThreadMapper::forumThreadToForumThreadDto);
    }

    public Page<ForumThreadDto> getMostActiveForumThreads(LocalDateTime startDate, Pageable pageable) {
        Page<ForumThread> forumThreads = forumThreadRepository.findMostActiveForumThreads(startDate, pageable);
        return forumThreads.map(forumThreadMapper::forumThreadToForumThreadDto); // Use the mapper
    }

    public Page<ForumThreadDto> getTrendingForumThreads(LocalDateTime startDate, Pageable pageable) {
        return forumThreadRepository.findTrendingForumThreads(startDate, pageable)
                .map(forumThreadMapper::forumThreadToForumThreadDto);
    }
}



