package com.example.imageboard.forum.validator;

import com.example.imageboard.forum.ForumRepository; // Assuming you have a repository for Forum
import com.example.imageboard.forum.dto.ForumDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ForumValidator implements Validator {

    private final ForumRepository forumRepository;

    public ForumValidator(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ForumDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ForumDto forumDto = (ForumDto) target;

        // Name Validation
        if (forumDto.getName() == null || forumDto.getName().isBlank()) {
            errors.rejectValue("name", "forum.name.empty", "Forum name is required.");
        } else if (forumDto.getName().length() > 100) { // Example length restriction
            errors.rejectValue("name", "forum.name.length", "Forum name must not exceed 100 characters.");
        } else if (forumNameExists(forumDto)) {
            errors.rejectValue("name", "forum.name.exists", "Forum with this name already exists.");
        }

        // Description Validation (Optional)
        if (forumDto.getDescription() != null && forumDto.getDescription().length() > 500) {
            errors.rejectValue("description", "forum.description.length", "Forum description must not exceed 500 characters.");
        }
    }

    // Helper method to check if a forum with the given name already exists
    private boolean forumNameExists(ForumDto forumDto) {
        return forumRepository.findByName(forumDto.getName())
                .filter(forum -> !forum.getId().equals(forumDto.getId())) // Exclude the forum being updated (if any)
                .isPresent();
    }
}