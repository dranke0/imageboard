package com.example.imageboard.forumThread.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forumThread.ForumThread;
import com.example.imageboard.forumThread.dto.ForumThreadDto;
import com.example.imageboard.user.User;
import com.example.imageboard.user.UserRepository;
import com.example.imageboard.forum.ForumRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ForumThreadMapper {

    @Autowired
    protected ForumRepository forumRepository;

    @Autowired
    protected UserRepository userRepository;


    @Mapping(target = "forum", source = "forum.id")
    @Mapping(target = "user", source = "author.id")
    public abstract ForumThread toForumThread(ForumThreadDto forumThreadDto);

    @Mapping(target = "forum", source = "forum") // Delegate forum mapping to ForumMapper
    @Mapping(target = "author", source = "user") // Delegate author mapping to PublicUserMapper
    public abstract ForumThreadDto forumThreadToForumThreadDto(ForumThread forumThread);

    @Mapping(target = "id", ignore = true)         // Don't map the ID, as it's already set in the existing entity
    @Mapping(target = "createdAt", ignore = true) // Don't update createdAt
    @Mapping(target = "updatedAt", ignore = true) // Don't update updatedAt (it'll be handled by @UpdateTimestamp)
    @Mapping(target = "forumThreads", ignore = true) // Don't update forumThreads
    public abstract void updateForumThreadFromForumThreadDto(ForumThreadDto forumThreadDto, @MappingTarget ForumThread forumThread);

    // Manually implemented method to map Forum ID to Forum entity
    protected Forum forumIdToForum(Long id) {
        if (id == null) {
            return null;
        } else {
            return forumRepository.findById(id).orElse(null);
        }
    }

    // Manually implemented method to map Author ID to User entity
    protected User authorIdToUser(Long id) {
        if (id == null) {
            return null;
        } else {
            return userRepository.findById(id).orElse(null);
        }
    }
}

