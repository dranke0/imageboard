package com.example.imageboard.user.mapper;

import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "status", target = "status.name")
    UserDto userToUserDTO(User user);

    @Mapping(source = "status.name", target = "status")
    User userDTOToUser(UserDto userDTO);
}
