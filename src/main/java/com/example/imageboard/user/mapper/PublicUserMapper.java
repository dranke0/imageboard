package com.example.imageboard.user.mapper;

import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.PublicUserDto;


public class PublicUserMapper {
    public PublicUserDto toPublicUserDto(User user) {
      PublicUserDto publicUserDto = new PublicUserDto();
        publicUserDto.setId(user.getId());
        publicUserDto.setUsername(user.getUsername());
        publicUserDto.setCreatedAt(user.getCreatedAt());
        return publicUserDto;
    }

    public User userToPublicUserDto(User user) {
        return null;
    }

    public User publicUserDtoToUser(User user) {
    }
}
