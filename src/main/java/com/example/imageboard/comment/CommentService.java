package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.comment.exception.CommentNotFoundException;
import com.example.imageboard.comment.exception.InvalidCommentException;
import com.example.imageboard.comment.mapper.CommentMapper;
import com.example.imageboard.comment.validator.CommentDtoValidator;
import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.ThreadRepository;
import com.example.imageboard.thread.exception.ThreadNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.DataBinder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ThreadRepository threadRepository;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, CommentDtoValidator commentDtoValidator, ThreadRepository threadRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.threadRepository = threadRepository;
    }

    public List<CommentDto> getAll() {
        return commentRepository.findAll().stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    public CommentDto getById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::commentToCommentDto)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }


    public CommentDto create(CommentDto commentDto, Long threadId, Long userId) {

        ForumThread forumThread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(threadId));

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
