package com.example.imageboard.user.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.regex.Pattern;

@Component
public class PasswordValidator {

    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

    public void validate(String password, Errors errors) {
        if (password == null) {
            errors.rejectValue("password", "password.null", "Password cannot be null");
        } else if (password.length() < 8) {
            errors.rejectValue("password", "password.length", "Password must be at least 8 characters long.");
        } else if (!isValidPassword(password)) {
            errors.rejectValue("password", "password.strength", "Password does not meet complexity requirements.");
        }
    }


    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                UPPERCASE_PATTERN.matcher(password).matches() &&
                LOWERCASE_PATTERN.matcher(password).matches() &&
                DIGIT_PATTERN.matcher(password).matches() &&
                SPECIAL_CHAR_PATTERN.matcher(password).matches();
    }
}