package com.example.imageboard.forumThread.mapper;

import com.example.imageboard.comment.mapper.CommentMapper;
import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.mapper.ForumMapper;
import com.example.imageboard.forumThread.ForumThread;
import com.example.imageboard.forumThread.dto.ForumThreadDto;
import com.example.imageboard.user.User;
import com.example.imageboard.user.UserRepository;
import com.example.imageboard.forum.ForumRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CommentMapper.class, ForumMapper.class})
public abstract class ForumThreadMapper {

    @Autowired
    protected ForumRepository forumRepository;

    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "status", source = "status")
    public abstract ForumThread toForumThread(ForumThreadDto forumThreadDto);

    @Mapping(target = "forumThreads", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "threadCount", ignore = true)
    public abstract ForumThreadDto forumThreadToForumThreadDto(ForumThread forumThread);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "forumThreads", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "forum", ignore = true)
    public abstract void updateForumThreadFromForumThreadDto(ForumThreadDto forumThreadDto, @MappingTarget ForumThread forumThread);

        // ... (potential custom mappings for nested types if needed)
    }

