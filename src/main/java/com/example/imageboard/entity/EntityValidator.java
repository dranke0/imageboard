package com.example.imageboard.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EntityValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EntityDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!(target instanceof EntityModel)) {
            log.warn("Object is not an instance of EntityModel. Skipping entity validation.");
            return;
        }

        EntityModel entityModel = (EntityModel) target;
        List<String> validationErrors = new ArrayList<>();

        // ID Validation
        if (entityModel.getId() == null || entityModel.getId() <= 0) {
            validationErrors.add("ID must be a positive integer");
        }

        // CreatedAt Validation
        LocalDateTime createdAt = entityModel.getCreatedAt();
        if (createdAt == null || createdAt.isAfter(LocalDateTime.now())) {
            validationErrors.add("Creation date must be in the past");
        }

        // UpdatedAt Validation (If applicable)
        LocalDateTime updatedAt = entityModel.getUpdatedAt();
        if (updatedAt != null && updatedAt.isAfter(LocalDateTime.now())) {
            validationErrors.add("Update date cannot be in the future");
        }

        if (!validationErrors.isEmpty()) {
            for (String errorMessage : validationErrors) {
                errors.rejectValue("entity", "invalid.entityModel", errorMessage);
            }
        }
    }
}


