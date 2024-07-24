package com.example.imageboard.user.mapper;

import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.AdminUserDto;

import java.util.List;
import java.util.stream.Collectors;

public class AdminUserMapper {
    AdminUserDto toDto(User user) {
        return AdminUserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .role(null)
                .build();
    }
    AdminUserDto toEntity(AdminUserDto adminUserDto) {
        return AdminUserDto.builder()
                .id(adminUserDto.getId())
                .username(adminUserDto.getUsername())
                .email(adminUserDto.getEmail())
                .avatarUrl(adminUserDto.getAvatarUrl())
                .role(adminUserDto.getRole())
                .build();
    }

    List<AdminUserDto> toDto(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    List<AdminUserDto> toEntity(List<AdminUserDto> adminUserDtos) {
        return adminUserDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
