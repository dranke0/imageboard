package com.example.imageboard.user.mapper;

import com.example.imageboard.role.Role;
import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.AuthenticatedUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AuthenticatedUserMapper {
    AuthenticatedUserDto toDto(User user);

    default List<String> mapRolesToRoleNames(List<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }
}

