package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.comment.exception.CommentNotFoundException;
import com.example.imageboard.comment.exception.InvalidCommentException; // New custom exception
import com.example.imageboard.comment.mapper.CommentMapper;
import com.example.imageboard.comment.validator.CommentDtoValidator;
import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.ThreadRepository;
import com.example.imageboard.thread.exception.ThreadNotFoundException;
import com.example.imageboard.user.User;
import com.example.imageboard.user.UserRepository;
import com.example.imageboard.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // Added for managing transactions
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CommentDtoValidator commentDtoValidator;
    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;

    public List<CommentDto> getAllComments() {
        return commentRepository.findAll().stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    public CommentDto getCommentById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::commentToCommentDto)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    public List<CommentDto> getCommentsByThreadId(Long threadId) {
        return commentRepository.findCommentsByThreadIdWithUserAndThread(threadId).stream() // Fetch eagerly to avoid N+1
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId).stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    public CommentDto createComment(CommentDto commentDto, Long threadId, Long userId) {
        // 1. Input Validation
        DataBinder binder = new DataBinder(commentDto);
        binder.addValidators(commentDtoValidator);
        binder.validate();

        if (binder.getBindingResult().hasErrors()) {
            throw new InvalidCommentException(binder.getBindingResult());
        }

        // 2. Fetch Associated Entities
        ForumThread forumThread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(threadId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // 3. Map, Set, and Save
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        comment.setForumThread(forumThread);
        comment.setUser(user);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.commentToCommentDto(savedComment);
    }

    public CommentDto updateComment(Long id, CommentDto updatedCommentDto) {
        return commentRepository.findById(id)
                .map(comment -> {
                    commentDtoValidator.validate(updatedCommentDto, new DataBinder(updatedCommentDto).getBindingResult());
                    commentMapper.updateCommentFromDto(updatedCommentDto, comment);
                    return commentMapper.commentToCommentDto(commentRepository.save(comment));
                })
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
