package com.example.imageboard.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$");
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;

        validateAndSanitizeUsername(userDto.getUsername(), errors);
        validateAndSanitizeEmail(userDto.getEmail(), errors);
        validatePassword(userDto.getPassword(), errors);
    }

    private void validateAndSanitizeUsername(String username, Errors errors) {
        if (username == null) {
            errors.rejectValue("username", "username.null", "Username cannot be null");
        } else {
            String sanitizedUsername = username.toLowerCase().trim();

            if (sanitizedUsername.isBlank()) {
                errors.rejectValue("username", "username.blank", "Username cannot be blank");
            } else if (sanitizedUsername.length() < 3 || sanitizedUsername.length() > 50) {
                errors.rejectValue("username", "username.length", "Username must be between 3 and 50 characters");
            } else if (userRepository.findByUsername(sanitizedUsername) != null) {
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
            } else if (userRepository.findByEmail(sanitizedEmail) != null) {
                errors.rejectValue("email", "email.duplicate", "Email already exists");
            }
        }
    }

    private void validatePassword(String password, Errors errors) {
        if (password == null) {
            errors.rejectValue("password", "password.null", "Password cannot be null");
        } else if (password.length() < 8) {
            errors.rejectValue("password", "password.length", "Password must be at least 8 characters long.");
        } else if (!isValidPassword(password)) { // Use the local isValidPassword method
            errors.rejectValue("password", "password.strength", "Password does not meet complexity requirements.");
        }
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                UPPERCASE_PATTERN.matcher(password).matches() &&
                LOWERCASE_PATTERN.matcher(password).matches() &&
                DIGIT_PATTERN.matcher(password).matches() &&
                SPECIAL_CHAR_PATTERN.matcher(password).matches();
    }
}

