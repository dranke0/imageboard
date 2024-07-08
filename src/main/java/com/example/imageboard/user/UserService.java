package com.example.imageboard.user;

import com.example.imageboard.entity.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService extends EntityService<User, Long, UserDto> {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // Constructor Injection with qualifiers
    public UserService(@Qualifier("userRepository") EntityRepository<User, Long> repository,
                       EntityValidator entityValidator,
                       @Qualifier("userMapper") EntityMapper<User, UserDto> entityMapper) {
        super(repository, entityValidator, entityMapper);
        this.userRepository = (UserRepository) repository;
        this.userMapper = (UserMapper) entityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    @Override
    public UserDto save(UserDto userDto) {
        log.debug("Saving user dto: {}", userDto);
        return super.save(userDto);
    }

    @Transactional
    public User save(User userEntity) { // Renamed save to saveEntity
        log.debug("Saving user entity: {}", userEntity);
        userRepository.save(userEntity);
        return userEntity; // Return the saved entity if needed
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmailIgnoreCase(email));
    }

    public Optional<User> findByResetToken(String token) {
        return Optional.ofNullable(userRepository.findByResetToken(token));
    }

    public List<User> searchUsers(String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Search query cannot be empty");
        }
        log.debug("Searching users with query: {}", query);
        return userRepository.searchUsers(query); // Call the new repository method
    }

    public Optional<UserDto> registerUser(UserDto userDto) {
        return Optional.of(userRepository.save(userDto));
    }
}


