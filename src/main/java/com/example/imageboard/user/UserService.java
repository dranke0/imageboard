package com.example.imageboard.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import java.util.Optional;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto create(@Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user)
        return userMapper.toDto(user);
    }

    public UserDto findByEmail(String email) {
       User user = userRepository.findByEmailIgnoreCase(email);
       return userMapper.toDto(user);
    }

    public UserDto findByResetToken(String token) {
        User user = userRepository.findByResetToken(token);
        return userMapper.toDto(user);
    }

    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDto).orElse(null);

    }

    public Optional<UserDto> findByUsername(String username) {
       User user = userRepository.findByUsernameIgnoreCase(username)
        return Optional.ofNullable(userMapper.toDto(user));
    }

    public UserDto update(@Valid UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userDto.getId()));
        User user  = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(existingUser));
    }

    public void delete(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userRepository.delete(userToDelete);
    }
}



