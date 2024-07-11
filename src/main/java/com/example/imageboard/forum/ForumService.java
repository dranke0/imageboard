package com.example.imageboard.forum; // Adjust the package if needed

import com.example.imageboard.forum.dto.ForumDto;
import com.example.imageboard.forum.exception.ForumNotFoundException;
import com.example.imageboard.forum.exception.InvalidForumException; // New custom exception
import com.example.imageboard.forum.mapper.ForumMapper;
import com.example.imageboard.forum.validator.ForumValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // Use @Transactional for all methods that modify data
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
        return forumRepository.findById(id)
                .map(forumMapper::forumToForumDto)
                .orElseThrow(() -> new ForumNotFoundException(id));
    }

    public ForumDto createForum(ForumDto forumDto) {
        BindingResult bindingResult = new BeanPropertyBindingResult(forumDto, "forumDto");
        forumValidator.validate(forumDto, bindingResult);  // Use BindingResult

        if (bindingResult.hasErrors()) {
            throw new InvalidForumException(bindingResult); // Throw custom exception
        }

        Forum forum = forumMapper.forumDtoToForum(forumDto);
        forum = forumRepository.save(forum);
        return forumMapper.forumToForumDto(forum);
    }

    public ForumDto updateForum(Long id, ForumDto updatedForumDto) {
        BindingResult bindingResult = new BeanPropertyBindingResult(updatedForumDto, "forumDto");
        forumValidator.validate(updatedForumDto, bindingResult);  // Use BindingResult

        if (bindingResult.hasErrors()) {
            throw new InvalidForumException(bindingResult); // Throw custom exception
        }

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
        List<Forum> forums = forumRepository.findAll(); // Replace with the actual query

        // Fetch threads eagerly for calculating threadCount if needed
        forums.forEach(f -> f.getForumThreads().size());

        List<ForumDto> forumDtos = forumMapper.forumsToForumDtos(forums);

        // Sort and limit the results
        return forumDtos.stream()
                .sorted((f1, f2) -> f2.getThreadCount().compareTo(f1.getThreadCount()))
                .limit(limit)
                .collect(Collectors.toList());
    }
}




