package com.example.imageboard.user.validator;

import com.example.imageboard.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$");
    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;

    private void validateAndSanitizeUsername(String username, Errors errors) {
        if (username == null) {
            errors.rejectValue("username", "username.null", "Username cannot be null");
        } else {
            String sanitizedUsername = username.toLowerCase().trim();
            if (sanitizedUsername.isBlank()) {
                errors.rejectValue("username", "username.blank", "Username cannot be blank");
            } else if (sanitizedUsername.length() < 3 || sanitizedUsername.length() > 50) {
                errors.rejectValue("username", "username.length", "Username must be between 3 and 50 characters");
            } else if (userRepository.findByUsername(sanitizedUsername).isPresent()) {
                errors.rejectValue("username", "username.duplicate", "Username already exists");
            }
        }
    }

    private void validateAndSanitizeEmail(String email, Errors errors) {
        if (email == null) {
            errors.rejectValue("email", "email.null", "Email cannot be null");
        } else {
            String sanitizedEmail = email.toLowerCase().trim();

            if (sanitizedEmail.isBlank()) {
                errors.rejectValue("email", "email.blank", "Email cannot be blank");
            } else if (!EMAIL_PATTERN.matcher(sanitizedEmail).matches()) {
                errors.rejectValue("email", "email.invalid", "Invalid email format");
            } else if (userRepository.findByEmail(sanitizedEmail).isPresent()) {
                errors.rejectValue("email", "email.duplicate", "Email already exists");
            }
        }
    }
}

