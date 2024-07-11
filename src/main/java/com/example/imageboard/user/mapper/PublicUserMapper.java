package com.example.imageboard.user.mapper;

import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.PublicUserDto;
import org.mapstruct.Mapper;

@Mapper
public interface PublicUserMapper {
    PublicUserDto toPublicUserDto(User user);
}
