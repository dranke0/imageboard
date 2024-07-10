package com.example.imageboard.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public User createUser(@Valid UserDto userDto, BindingResult bindingResult) {
        userValidator.validate(userDto, bindingResult);
        if(bindingResult.hasErrors()) {
            throw new InvalidUserException(bindingResult);
        }
        User user = User.builder()
                .username(userDto.getUsername().toLowerCase())
                .email(userDto.getEmail().toLowerCase())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(UserRole.USER)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto updatedUserDto) {
        return userRepository.findById(id)
                .map(user -> user.toBuilder() // Use toBuilder to create a new builder from the existing user
                        .username(updatedUserDto.getUsername() != null ? updatedUserDto.getUsername().toLowerCase().trim() : user.getUsername())
                        .email(updatedUserDto.getEmail() != null ? updatedUserDto.getEmail().toLowerCase().trim() : user.getEmail())
                        .password(updatedUserDto.getPassword() != null ? passwordEncoder.encode(updatedUserDto.getPassword()) : user.getPassword())
                        .authorities(updatedUserDto.getAuthorities() != null ? updatedUserDto.getAuthorities() : user.getAuthorities())
                        .build())
                .map(userRepository::save)
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public void deleteUser(Long id) {
        userRepository.findById(id)
                .ifPresent(userRepository::delete);
    }
}




