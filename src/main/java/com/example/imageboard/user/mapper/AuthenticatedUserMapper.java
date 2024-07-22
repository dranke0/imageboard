package com.example.imageboard.user.mapper;

import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.AuthenticatedUserDto;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticatedUserMapper {
    public AuthenticatedUserDto toDto(User user) {
        return AuthenticatedUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }
    User toEntity(AuthenticatedUserDto authenticatedUserDto) {
        return User.builder()
                .id(authenticatedUserDto.getId())
                .username(authenticatedUserDto.getUsername())
                .email(authenticatedUserDto.getEmail())
                .avatarUrl(authenticatedUserDto.getAvatarUrl())
                .role(authenticatedUserDto.getRole())
                .build();
    }

    List<AuthenticatedUserDto> toDto(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<User> toEntity(List<AuthenticatedUserDto> authenticatedUserDtos) {
        return authenticatedUserDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

