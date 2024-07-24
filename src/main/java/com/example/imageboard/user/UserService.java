package com.example.imageboard.user;

import com.example.imageboard.user.dto.AuthenticatedUserDto;
import com.example.imageboard.user.dto.PublicUserDto;
import com.example.imageboard.user.exception.InvalidUserException;
import com.example.imageboard.user.mapper.AuthenticatedUserMapper;
import com.example.imageboard.user.mapper.PublicUserMapper;
import com.example.imageboard.user.validator.UserValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PublicUserMapper publicUserMapper;
    private final AuthenticatedUserMapper authenticatedUserMapper;
    private final UserValidator userValidator;

    public List<PublicUserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(publicUserMapper::toDto)
                .toList();
    }

    public PublicUserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return publicUserMapper.toDto(user);
    }

    @Transactional
    public AuthenticatedUserDto createUser(AuthenticatedUserDto userDto, BindingResult bindingResult) throws InvalidUserException {
        userValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new InvalidUserException(bindingResult);
        }

        User user = User.builder()
                .username(userDto.getUsername().toLowerCase())
                .email(userDto.getEmail().toLowerCase())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(userDto.getRole()) // Assuming you have a way to get role entities from role names
                .status(UserStatus.ACTIVE) // Set initial status to ACTIVE
                .build();

        userRepository.save(user);
        return authenticatedUserMapper.toDto(user);
    }

    @Transactional
    public AuthenticatedUserDto updateUser(Long id, AuthenticatedUserDto updatedUserDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        if (updatedUserDto.getUsername() != null) {
            user.setUsername(updatedUserDto.getUsername().toLowerCase().trim());
        }

        if (updatedUserDto.getEmail() != null) {
            user.setEmail(updatedUserDto.getEmail().toLowerCase().trim());
        }

        if (updatedUserDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUserDto.getPassword()));
        }

        // Update roles if necessary
        if (updatedUserDto.getRole() != null) {
            user.setRole(updatedUserDto.getRole());
        }

        User savedUser = userRepository.save(user);
        return authenticatedUserMapper.toDto(savedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}




