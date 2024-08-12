package com.example.imageboard.user;

import com.example.imageboard.status.Status;
import com.example.imageboard.user.dto.UserDto;
import com.example.imageboard.user.exception.InvalidUserException;
import com.example.imageboard.user.mapper.UserMapper;
import com.example.imageboard.user.validator.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    // Get all users
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDTO)
                .toList();
    }

    // Get user by ID
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return userMapper.userToUserDTO(user);
    }

    // Create a new user (registration)
    @Transactional
    public UserDto createUser(UserDto userDto) throws InvalidUserException {

        // Map DTO to entity and set additional fields
        User user = User.builder()
                .username(userDto.getUsername().toLowerCase().trim())
                .email(userDto.getEmail().toLowerCase().trim())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .status(Status.valueOf("ACTIVE"))
                .build();

        // Save the new user
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    // Update an existing user
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Use the update method to create a new instance with updated fields
        User updatedUser = User.builder()
                .username(userDto.getUsername().toLowerCase().trim())
                .email(userDto.getEmail().toLowerCase().trim())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .avatarUrl(userDto.getAvatarUrl())
                .status(userDto.getStatus() != null ? Status.valueOf(userDto.getStatus().toUpperCase()))
                .build();

        // Save and return the updated user
        User savedUser = userRepository.save(updatedUser);
        return userMapper.userToUserDTO(savedUser);
    }

    // Delete a user by ID
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
