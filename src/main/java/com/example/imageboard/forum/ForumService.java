package com.example.imageboard.forum; // Adjust the package if needed

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.forum.mapper.ForumMapper;
import com.example.imageboard.forum.validator.ForumValidator; // Add ForumValidator
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional // Use @Transactional for all methods that modify data
public class ForumService {
    private final ForumRepository forumRepository;
    private final ForumMapper forumMapper;
    private final ForumValidator forumValidator;

    public List<ForumDto> getAllForums() {
        List<Forum> forums = forumRepository.findAllForums(); // Use the appropriate method
        return forumMapper.forumsToForumDtos(forums);
    }

    public List<ForumDto> getAllForumsWithThreads() {
        List<Forum> forums = forumRepository.findAllForumsWithThreads();
        return forumMapper.forumsToForumDtos(forums);
    }


    public ForumDto getForumById(Long id) {
        Forum forum = forumRepository.findById(id)
                .orElseThrow(() -> new ForumNotFoundException(id));
        return forumMapper.forumToForumDto(forum);
    }

    public ForumDto createForum(ForumDto forumDto) {
        // Validate using the ForumValidator
        forumValidator.validate(forumDto);

        Forum forum = forumMapper.forumDtoToForum(forumDto);
        forum = forumRepository.save(forum);
        return forumMapper.forumToForumDto(forum);
    }

    public ForumDto updateForum(Long id, ForumDto updatedForumDto) {
        // Validate using the ForumValidator
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
        // Note: You'll need to implement the logic for determining "most active" in your repository
        List<Forum> forums = forumRepository.findAll(); // Replace with your actual query

        // If you choose to calculate threadCount on the fly, you need to fetch the threads here:
        forums.forEach(f -> f.getForumThreads().size()); // Trigger fetching

        return forumMapper.forumsToForumDtos(forums);
    }
}

