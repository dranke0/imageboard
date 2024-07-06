package com.example.imageboard.user;

import com.example.imageboard.entity.EntityValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator extends EntityValidator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors); // Call entity validation first

        User user = (User) target;

        // Add UserDto-specific validation rules here (e.g., email format, password strength, etc.)
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            errors.rejectValue("email", "invalid.email", "Invalid email format");
        }
        // ... other validation rules
    }
}