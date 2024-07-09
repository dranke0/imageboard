package com.example.imageboard.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Single Entity to DTO (with authorities mapping)
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))")
    Optional<UserDto> toDto(Optional<User> user);

    // Single DTO to Entity (ignoring authorities)
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "authorities", ignore = true)
    Optional<User> toEntity(Optional<UserDto> userDto);

    // List of Entities to List of DTOs
    List<UserDto> toDtoList(List<User> users);

    // List of DTOs to List of Entities
    List<User> toEntityList(List<UserDto> userDtos);
}



