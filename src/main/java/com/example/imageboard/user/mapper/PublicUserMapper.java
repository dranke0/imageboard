package com.example.imageboard.user.mapper;

import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.PublicUserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PublicUserMapper {
    public PublicUserDto toDto(User user);
    public User toEntity(User user);
    public List<PublicUserDto> toDto(List<User> user);
    public List<User> toEntity(List<User> user);
}
