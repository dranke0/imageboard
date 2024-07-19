package com.example.imageboard.user.validator;

import com.example.imageboard.user.UserRepository;
import com.example.imageboard.user.dto.AuthenticatedUserDto;
import com.example.imageboard.user.dto.PublicUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$");
    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator; // Inject the com.example.imageboard.user.validator.PasswordValidator

    @Override
    public boolean supports(Class<?> clazz) {
        return PublicUserDto.class.isAssignableFrom(clazz) || AuthenticatedUserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof PublicUserDto) {
            validatePublicUserDto((PublicUserDto) target, errors);
        } else if (target instanceof AuthenticatedUserDto) {
            validateAuthenticatedUserDto((AuthenticatedUserDto) target, errors);
        }
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

    private void validatePublicUserDto(PublicUserDto userDto, Errors errors) {
        validateAndSanitizeUsername(userDto.getUsername(), errors);
    }

    private void validateAuthenticatedUserDto(AuthenticatedUserDto userDto, Errors errors) {
        validateAndSanitizeUsername(userDto.getUsername(), errors);
        validateAndSanitizeEmail(userDto.getEmail(), errors);
        passwordValidator.validate(userDto.getPassword(), errors);  // Use com.example.imageboard.user.validator.PasswordValidator
    }
}

// Separate com.example.imageboard.user.validator.PasswordValidator Class
