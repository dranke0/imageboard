package com.example.imageboard.comment;

import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.comment.exception.CommentNotFoundException;
import com.example.imageboard.comment.mapper.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDto> getAll() {
        return commentRepository.findAll().stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentDto getById(Long id) {
        return commentRepository.findById(id)
                .map(commentMapper::toDto)
                .orElseThrow(() -> new CommentNotFoundException(id));
    }

    public void create(CommentDto commentDto) {
        Comment comment = commentMapper.toEntity(commentDto);
        commentRepository.save(comment);
    }

    public void update(Long id, CommentDto updatedCommentDto) {
        if (commentRepository.findById(id).isPresent())
            commentRepository.save(commentMapper.toEntity(updatedCommentDto));
        else
            throw new CommentNotFoundException(id);
    }

    public void deleteComment(Long id) {
        if (commentRepository.findById(id).isPresent())
            commentRepository.deleteById(id);
        else
            throw new CommentNotFoundException(id);
    }
}
