package com.example.imageboard.user.mapper;

import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.AuthenticatedUserDto;
import org.mapstruct.Mapper;

@Mapper
public interface AuthenticatedUserMapper {
    AuthenticatedUserDto toAuthenticatedUserDto(User user);
}
