package com.example.imageboard.forum.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.dto.ForumDto;
import java.util.List;
import java.util.stream.Collectors;


public class ForumMapper {
    public ForumDto toDto(Forum forum) {
        if (forum == null) {
            return null;
        }
        return ForumDto.builder()
                .id(forum.getId())
                .name(forum.getName())
                .description(forum.getDescription())
                .build();

    }
    public Forum toEntity(ForumDto forumDto) {
        if (forumDto == null) {
            return null;
        }
        return Forum.builder()
                .id(forumDto.getId())
                .name(forumDto.getName())
                .description(forumDto.getDescription())
                .build();

    }
    public List<Forum> toEntity(List<ForumDto> forumDtos) {
        if (forumDtos == null) {
            return null;
        }
        return forumDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
    public List<ForumDto> toDto(List<Forum> forums) {
        if (forums == null) {
            return null;
        }
        return forums.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}




