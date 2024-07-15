package com.example.imageboard.forum;

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.forum.mapper.ForumMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ForumDto create(ForumDto forumDto) {
        Forum forum = forumMapper.toEntity(forumDto);
        Forum savedForum = forumRepository.save(forum);
        return forumMapper.toDto(savedForum);
    }

    public ForumDto update(Long id, ForumDto forumDto) {
        return forumRepository.findById(id)
                .map(existingForum -> {
                    existingForum.setName(forumDto.getName());
                    existingForum.setDescription(forumDto.getDescription());
                    return forumMapper.toDto(forumRepository.save(existingForum));
                })
                .orElseThrow(() -> new ForumNotFoundException(id));
    }

    public void delete(Long id) {
        forumRepository.deleteById(id);
    }
}



