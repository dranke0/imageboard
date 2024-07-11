package com.example.imageboard.forum;

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.forum.mapper.ForumMapper;
import com.example.imageboard.forum.validator.ForumValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ForumService {
    private final ForumRepository forumRepository;
    private final ForumMapper forumMapper;
    private final ForumValidator forumValidator;

    public List<ForumDto> getAllForums() {
        return forumRepository.findAllForums().stream()
                .map(forumMapper::forumToForumDto)
                .collect(Collectors.toList());
    }

    public List<ForumDto> getAllForumsWithThreads() {
        return forumRepository.findAllForumsWithThreads().stream()
                .map(forumMapper::forumToForumDto)
                .collect(Collectors.toList());
    }

    public ForumDto getForumById(Long id) {
        Forum forum = forumRepository.findById(id)
                .orElseThrow(() -> new ForumNotFoundException(id));
        return forumMapper.forumToForumDto(forum);
    }

    public ForumDto createForum(ForumDto forumDto) {
        forumValidator.validate(forumDto);

        Forum forum = forumMapper.forumDtoToForum(forumDto);
        forum = forumRepository.save(forum);
        return forumMapper.forumToForumDto(forum);
    }

    public ForumDto updateForum(Long id, ForumDto updatedForumDto) {
        forumValidator.validate(updatedForumDto);

        Forum existingForum = forumRepository.findById(id)
                .orElseThrow(() -> new ForumNotFoundException(id));
        forumMapper.updateForumFromDto(updatedForumDto, existingForum);
        return forumMapper.forumToForumDto(existingForum);
    }

    public void deleteForum(Long id) {
        if (!forumRepository.existsById(id)) {
            throw new ForumNotFoundException(id);
        }
        forumRepository.deleteById(id);
    }

    public long getTotalForums() {
        return forumRepository.count();
    }

    public List<ForumDto> getMostActiveForums(int limit) {
        List<Forum> forums = forumRepository.findAll(); // Replace with your actual query

        // Fetch threads if you need the threadCount in the DTO
        forums.forEach(f -> f.getForumThreads().size());

        List<ForumDto> forumDtos = forumMapper.forumsToForumDtos(forums);
        // Sort by thread count in descending order and limit
        return forumDtos.stream()
                .sorted((f1, f2) -> f2.getThreadCount().compareTo(f1.getThreadCount()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}



