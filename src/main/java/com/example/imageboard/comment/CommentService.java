package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.comment.exception.CommentNotFoundException;
import com.example.imageboard.comment.mapper.CommentMapper;
import com.example.imageboard.comment.validator.CommentDtoValidator;
import com.example.imageboard.forumThread.ForumThread;
import com.example.imageboard.forumThread.ForumThreadRepository;
import com.example.imageboard.user.User;
import com.example.imageboard.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CommentDtoValidator commentDtoValidator;
    private final UserRepository userRepository;
    private final ForumThreadRepository forumThreadRepository;

    public Page<CommentDto> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable).map(commentMapper::commentToCommentDto);
    }

    public CommentDto getCommentById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::commentToCommentDto)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    public Page<CommentDto> getCommentsByThreadId(Long threadId, Pageable pageable) {
        return commentRepository.findByForumThreadId(threadId, pageable)
                .map(commentMapper::commentToCommentDto);
    }

    public Page<CommentDto> getCommentsByUserId(Long userId, Pageable pageable) {
        return commentRepository.findByUserId(userId, pageable)
                .map(commentMapper::commentToCommentDto);
    }

    public CommentDto createComment(CommentDto commentDto, Long threadId, Long userId) {
        DataBinder binder = new DataBinder(commentDto);
        binder.addValidators(commentDtoValidator);
        binder.validate();
        BindingResult bindingResult = binder.getBindingResult();

        if (bindingResult.hasErrors()) {
            // Throw a custom exception or return a response with validation errors
        }

        ForumThread forumThread = forumThreadRepository.findById(threadId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid thread ID: " + threadId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));

        Comment comment = commentMapper.commentDtoToComment(commentDto);
        comment.setForumThread(forumThread);
        comment.setUser(user);

        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }

    public CommentDto updateComment(Long id, CommentDto updatedCommentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));

        commentMapper.updateCommentFromDto(updatedCommentDto, comment);

        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}



