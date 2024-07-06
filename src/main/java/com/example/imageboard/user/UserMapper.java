package com.example.imageboard.user;

import com.example.imageboard.entity.EntityMapper;

import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends EntityMapper<User, UserDto> {

    public UserMapper(@NonNull ModelMapper modelMapper) {
        super(modelMapper);
    }
}
