package com.example.imageboard.user;

import com.example.imageboard.entity.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserMapper implements EntityMapper<User, UserDto> {

    @NonNull
    private final ModelMapper modelMapper = new ModelMapper();
    private final Logger logger = LoggerFactory.getLogger(EntityMapper.class);

    public UserDto mapEntityToDto(User userEntity) throws UserNotFoundException {
        return Optional.ofNullable(userEntity)
                .map(e -> modelMapper.map(e, UserDto.class))
                .orElseThrow(() -> {
                    logger.warn("Attempting to map null entity");
                    return new UserNotFoundException("User cannot be null");
                });
    }

    public User mapDtoToEntity(UserDto userDto) throws EntityNotFoundException {
        return Optional.ofNullable(userDto)
                .map(d -> modelMapper.map(d, User.class))
                .orElseThrow(() -> {
                    logger.warn("Attempting to map null DTO");
                    return new UserNotFoundException("DTO cannot be null");
                });
    }

    public List<UserDto> mapEntitiesToDtos(List<User> entities) {
        return entities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    public List<User> mapDtosToEntities(List<UserDto> dtos) {
        return dtos.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());
    }
}
