package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.comment.exception.CommentNotFoundException;
import com.example.imageboard.comment.mapper.CommentMapper;
import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.ThreadRepository;
import com.example.imageboard.thread.exception.ThreadNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ThreadRepository threadRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, ThreadRepository threadRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.threadRepository = threadRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDto> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        return commentMapper.toDto(comment);
    }

    public CommentDto create(Long threadId, CommentDto commentDto) {
        ForumThread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(threadId));

        Comment comment = commentMapper.toEntity(commentDto);
        comment.setThread(thread);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    public CommentDto update(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));

        comment.setContent(commentDto.getContent());

        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toDto(updatedComment);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    public CommentDto findById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
        return commentMapper.toDto(comment);
    }

    public List<CommentDto> findAllByThreadId(Long threadId) {
        List<Comment> comments = commentRepository.findAllByThreadId(threadId);
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }
}

