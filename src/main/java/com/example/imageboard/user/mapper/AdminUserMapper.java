package com.example.imageboard.user.mapper;

import com.example.imageboard.user.User;
import com.example.imageboard.user.dto.AdminUserDto;
import org.mapstruct.Mapper;

@Mapper(uses = AuthenticatedUserMapper.class) // Inherit mappings from AuthenticatedUserMapper
public interface AdminUserMapper {
    AdminUserDto toDto(User user);
}
