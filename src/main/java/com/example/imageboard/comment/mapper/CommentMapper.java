package com.example.imageboard.comment.mapper;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.comment.dto.CommentDto;
import com.example.imageboard.thread.ForumThread;
import com.example.imageboard.thread.ThreadRepository;
import com.example.imageboard.thread.exception.ThreadNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private final ThreadRepository threadRepository;

    public CommentMapper(ThreadRepository threadRepository) {
        this.threadRepository = threadRepository;
    }

    public CommentDto toDto(Comment comment) {

        if (comment == null) {
            return null;
        }

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setImageUrl(comment.getImageUrl());
        commentDto.setThreadId(comment.getThread().getId());
        return commentDto;
    }

    public Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }

        ForumThread thread = threadRepository.findById(commentDto.getThreadId())
                .orElseThrow(() -> new ThreadNotFoundException(commentDto.getThreadId()));

        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setImageUrl(commentDto.getImageUrl());
        comment.setThread(thread);
        return comment;
    }
}

