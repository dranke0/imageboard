package com.example.imageboard.forum.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.dto.ForumDto;

public class ForumMapper {

    public ForumDto toDto(Forum forum) {
        ForumDto forumDto = new ForumDto();
        forumDto.setName(forum.getName());
        forumDto.setDescription(forum.getDescription());
        return forumDto;
    }

    public Forum toEntity(ForumDto forumDto){
        Forum forum = new Forum();
        forum.setName(forumDto.getName());
        forum.setDescription(forumDto.getDescription());
        return forum;
    }
}




