package com.example.imageboard.user;

import com.example.imageboard.base.BaseMapper;

import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<User, UserDto> {

    @Override
    public UserDto toDto(User user) {
    }
}
