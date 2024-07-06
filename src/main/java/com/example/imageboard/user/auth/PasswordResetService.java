package com.example.imageboard.user.auth;

import com.example.imageboard.user.User;
import com.example.imageboard.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime; // Add for token expiration
import java.util.UUID;

@Service
public class PasswordResetService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public PasswordResetService(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    /*public void sendResetEmail(String email) {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email); // Explicit error
        }

        String token = UUID.randomUUID().toString();
        userRepository.findByResetToken(token);
        user.setResetTokenExpiration(LocalDateTime.now().plusHours(24)); // Set 24-hour expiration
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Imageboard Password Reset"); // Customize subject
        message.setText(
                "You have requested to reset your password. Please click on the following link to complete the process:\n" +
                        "http://your-imageboard-domain/reset-password?token=" + token + "\n\n" +
                        "This link will expire in 24 hours."
        );
        mailSender.send(message);
    } */

    // ... other methods for validating token, resetting password, etc.
}

