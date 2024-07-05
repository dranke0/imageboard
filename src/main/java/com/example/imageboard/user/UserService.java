package com.example.imageboard.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public User findByResetToken(String token) {
        return userRepository.findByResetToken(token);
    }

    public void save(User user) {
        userRepository.save(user);
    }

}


