package com.example.imageboard.forum;

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.forum.mapper.ForumMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class ForumService {
    private final ForumRepository forumRepository;
    private final ForumMapper forumMapper;

    public ForumService(ForumRepository forumRepository, ForumMapper forumMapper) {
        this.forumRepository = forumRepository;
        this.forumMapper = forumMapper;
    }

    public ForumDto get(Long id) {
        return forumRepository.findById(id)
                .map(forumMapper::toDto)
                .orElseThrow(() -> new ForumNotFoundException(id));
    }

    public void create(ForumDto forumDto) {
        Optional.of(new Forum(forumDto.getName(), forumDto.getDescription()))
                .map(forumRepository::save)
                .map(forumMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Failed to create forum"));
    }

    public void update(Long id, ForumDto forumDto) {
        forumRepository.findById(id)
                .orElseThrow(() -> new ForumNotFoundException(id));
                forumRepository.save(forumMapper.toEntity(forumDto));
    }

    public void delete(Long id) {
        Forum forum = forumRepository.findById(id)
                .orElseThrow(() -> new ForumNotFoundException(id));
        forumRepository.delete(forum);
    }
}



