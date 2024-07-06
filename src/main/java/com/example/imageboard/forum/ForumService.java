package com.example.imageboard.forum;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class ForumService {

    private static final Logger logger = LoggerFactory.getLogger(ForumService.class);

    private final ForumRepository forumRepository;
    private final ModelMapper modelMapper;

    public List<ForumDto> getAllBoards() {
        List<Forum> forums = forumRepository.findAll();
        logger.info("Fetched {} forums", forums.size());
        return forums.stream()
                .map(forum -> modelMapper.map(forum, ForumDto.class))
                .collect(Collectors.toList());
    }

    public ForumDto getBoardById(Long id) {
        return forumRepository.findById(id)
                .map(forum -> modelMapper.map(forum, ForumDto.class))
                .orElseThrow(() -> new ResourceNotFoundException("Forum", id.toString()));
    }

    @Transactional
    public ForumDto createBoard(@Valid ForumDto forumDto) {
        Forum forum = modelMapper.map(forumDto, Forum.class);
        return modelMapper.map(forumRepository.save(forum), ForumDto.class);
    }

    @Transactional
    public ForumDto updateBoard(Long id, @Valid ForumDto updatedForumDto) {
        Forum existingForum = forumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forum", id.toString()));
        modelMapper.map(updatedForumDto, existingForum); // Update the existing forum
        return modelMapper.map(forumRepository.save(existingForum), ForumDto.class);
    }

    public void deleteBoard(Long id) {
        if (!forumRepository.existsById(id)) {
            throw new ResourceNotFoundException("Forum", id.toString());
        }
        forumRepository.deleteById(id);
    }
    private long getTotalBoards() {
        return forumRepository.count();
    }
    public Page<Forum> getMostActiveBoards(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return forumRepository.findMostActiveBoards(pageable);
    }
}