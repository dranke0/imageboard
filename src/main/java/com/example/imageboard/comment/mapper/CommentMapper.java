package com.example.imageboard.comment.mapper;

import com.example.imageboard.comment.Comment;

import com.example.imageboard.comment.dto.CommentDto;

public class CommentMapper {
    public Comment toEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setImageUrl(commentDto.getImageUrl());
        comment.setForumThreadId(commentDto.getForumThreadId());
        return comment;
    }

    public CommentDto toDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent(comment.getContent());
        commentDto.setImageUrl(comment.getImageUrl());
        commentDto.setForumThreadId(comment.getForumThreadId());
        return commentDto;
    }
}

