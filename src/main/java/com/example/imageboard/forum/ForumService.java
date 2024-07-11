package com.example.imageboard.forum;

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forum.exception.ForumNotFoundException;  // New custom exception
import com.example.imageboard.forum.mapper.ForumMapper; // Inject the ForumMapper
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {
    private final ForumRepository forumRepository;
    private final ForumMapper forumMapper; // Assuming you're using MapStruct or a similar mapper

    public List<ForumDto> getAllForums() {
        return forumRepository.findAll().stream()
                .map(forumMapper::toForumDto)
                .toList();
    }

    public ForumDto getForumById(Long id) {
        Forum forum = forumRepository.findById(id)
                .orElseThrow(() -> new ForumNotFoundException(id)); // Custom exception
        return forumMapper.toForumDto(forum);
    }

    @Transactional
    public ForumDto createForum(@Valid ForumDto forumDto) {
        Forum forum = forumMapper.toForum(forumDto);  // Map DTO to entity
        forum = forumRepository.save(forum);
        return forumMapper.toForumDto(forum);
    }

    @Transactional
    public ForumDto updateForum(Long id, @Valid ForumDto updatedForumDto) {
        Forum existingForum = forumRepository.findById(id)
                .orElseThrow(() -> new ForumNotFoundException(id));
        forumMapper.updateForumFromDto(updatedForumDto, existingForum); // Update entity using mapper
        existingForum = forumRepository.save(existingForum);
        return forumMapper.toForumDto(existingForum);
    }

    @Transactional
    public void deleteForum(Long id) {
        if (!forumRepository.existsById(id)) {
            throw new ForumNotFoundException(id);
        }
        forumRepository.deleteById(id);
    }

    public long getTotalForums() {
        return forumRepository.count();
    }

    public Page<ForumDto> getMostActiveForums(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return forumRepository.findAllForumDtos(pageable);
    }
}
