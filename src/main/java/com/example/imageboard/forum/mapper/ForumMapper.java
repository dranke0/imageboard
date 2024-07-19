package com.example.imageboard.forum.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.dto.ForumDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ForumMapper {
    public ForumDto toDto(Forum forum);
    public Forum toEntity(ForumDto forumDto);
    public List<Forum> toEntity(List<ForumDto> forumDtos);
    public List<ForumDto> toDto(List<Forum> forums);
}




