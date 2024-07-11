package com.example.imageboard.comment.validator;

import com.example.imageboard.comment.dto.CommentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class CommentDtoValidator implements Validator {

    private static final int MAX_CONTENT_LENGTH = 5000;

    @Override
    public boolean supports(Class<?> clazz) {
        return CommentDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CommentDto commentDto = (CommentDto) target;

        // Content Validation
        validateContent(commentDto.getContent(), errors);

        // Image URL Validation
        validateImageUrl(commentDto.getImageUrl(), errors);

        // User Validation (optional, depends on your use case)
        if (commentDto.getUser() == null) {
            errors.rejectValue("user", "comment.user.empty", "User is required.");
        }

        // Thread ID Validation (optional, depends on your use case)
        if (commentDto.getThreadId() == null || commentDto.getThreadId() <= 0) {
            errors.rejectValue("threadId", "comment.threadId.invalid", "Thread ID must be a positive number.");
        }
    }

    private void validateContent(String content, Errors errors) {
        if (content == null || content.isBlank()) {
            errors.rejectValue("content", "comment.content.empty", "Content is required.");
        } else if (content.length() > MAX_CONTENT_LENGTH) {
            errors.rejectValue("content", "comment.content.length",
                    String.format("Content must not exceed %d characters.", MAX_CONTENT_LENGTH));
        }
    }

    private void validateImageUrl(String imageUrl, Errors errors) {
        if (imageUrl == null || imageUrl.isBlank()) {
            errors.rejectValue("imageUrl", "comment.imageUrl.empty", "Image URL is required.");
        } else {
            try {
                new URL(imageUrl);
            } catch (MalformedURLException e) {
                errors.rejectValue("imageUrl", "comment.imageUrl.invalid", "Invalid image URL format.");
            }
        }
    }
}
