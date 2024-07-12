package com.example.imageboard.forumThread.mapper;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.comment.mapper.CommentMapper;
import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.mapper.ForumMapper;
import com.example.imageboard.forumThread.ForumThread;
import com.example.imageboard.forumThread.dto.ForumThreadDto;
import com.example.imageboard.user.User;
import com.example.imageboard.user.mapper.PublicUserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ForumThreadMapper {

    private final CommentMapper commentMapper;
    private final ForumMapper forumMapper;
    private final PublicUserMapper publicUserMapper;

    public ForumThreadMapper(CommentMapper commentMapper, ForumMapper forumMapper, PublicUserMapper publicUserMapper) {
        this.commentMapper = commentMapper;
        this.forumMapper = forumMapper;
        this.publicUserMapper = publicUserMapper;
    }

    public ForumThread toForumThread(ForumThreadDto forumThreadDto) {
        if (forumThreadDto == null) {
            return null;
        }

        ForumThread forumThread = new ForumThread();
        forumThread.setTitle(forumThreadDto.getTitle());
        forumThread.setStatus(forumThreadDto.getStatus());
        forumThread.setCreatedAt(forumThreadDto.getCreatedAt());
        forumThread.setUpdatedAt(forumThreadDto.getUpdatedAt());

        // Map the user using PublicUserMapper
        forumThread.setUser(publicUserMapper.publicUserDtoToUser(forumThreadDto.getUser()));

        // Map the forum using ForumMapper
        forumThread.setForum(forumMapper.forumDtoToForum(forumThreadDto.getForum()));

        // Map the comments using CommentMapper
        forumThread.setComments(forumThreadDto.getComments().stream()
                .map(commentMapper::commentDtoToComment)
                .collect(Collectors.toList()));

        return forumThread;
    }

    public ForumThreadDto forumThreadToForumThreadDto(ForumThread forumThread) {
        if (forumThread == null) {
            return null;
        }

        ForumThreadDto forumThreadDto = new ForumThreadDto();
        forumThreadDto.setId(forumThread.getId());
        forumThreadDto.setTitle(forumThread.getTitle());
        forumThreadDto.setStatus(forumThread.getStatus());
        forumThreadDto.setCreatedAt(forumThread.getCreatedAt());
        forumThreadDto.setUpdatedAt(forumThread.getUpdatedAt());

        // Map the user to PublicUserDto using PublicUserMapper
        forumThreadDto.setUser(publicUserMapper.userToPublicUserDto(forumThread.getUser()));

        // Map the forum to ForumDto using ForumMapper
        forumThreadDto.setForum(forumMapper.forumToForumDto(forumThread.getForum()));

        // Map the comments to CommentDto using CommentMapper
        forumThreadDto.setComments(forumThread.getComments().stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList()));

        return forumThreadDto;
    }
}



