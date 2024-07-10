package com.example.imageboard.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// UserMapper.java
@Mapper(componentModel = "spring") // For Spring integration
public interface UserMapper {
    UserDto toDto(User user);
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);
    List<User> toEntityList(List<UserDto> userDtos);
}




