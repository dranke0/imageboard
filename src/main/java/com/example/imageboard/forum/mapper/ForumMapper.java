package com.example.imageboard.forum.mapper;

import com.example.imageboard.forum.Forum;
import com.example.imageboard.forum.dto.ForumDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ForumMapper {
    /*@Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")*/
    public ForumDto toDto(Forum forum);
    public Forum toEntity(ForumDto forumDto);
    public List<Forum> toEntity(List<ForumDto> forumDtos);
    public List<ForumDto> toDto(List<Forum> forums);
}




