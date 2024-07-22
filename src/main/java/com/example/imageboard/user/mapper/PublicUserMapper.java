package com.example.imageboard.user.mapper;

import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.PublicUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class PublicUserMapper {
    public PublicUserDto toDto(User user) {
        return PublicUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .build();

    }
    public User toEntity(User user) {
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }
    public List<PublicUserDto> toDto(List<User> user) {
        return user.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    public List<User> toEntity(List<User> user) {
        return user.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
