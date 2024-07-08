package com.example.imageboard.entity;

import com.example.imageboard.entity.implementation.EntityDtoImpl;
import com.example.imageboard.entity.implementation.EntityModelImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Slf4j
@Component
public class EntityValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EntityModelImpl.class.isAssignableFrom(clazz) || EntityDtoImpl.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof EntityModel) {
            validateBaseEntity((EntityModelImpl) target, errors);
        } else if (target instanceof EntityDtoImpl) {
            validateEntityDto((EntityDtoImpl) target, errors);
        } else {
            log.warn("Unsupported object type for validation: {}", target.getClass().getName());
        }
    }

    private void validateBaseEntity(EntityModelImpl entity, Errors errors) {
        validateId(entity.getId(), errors);
        validateCreatedAt(entity.getCreatedAt(), errors);
        validateUpdatedAt(entity.getUpdatedAt(), errors);
    }

    private void validateEntityDto(EntityDtoImpl dto, Errors errors) {
        validateId(dto.getId(), errors);
        validateCreatedAt(dto.getCreatedAt(), errors);
        validateUpdatedAt(dto.getUpdatedAt(), errors);
    }

    private void validateId(Long id, Errors errors) {
        if (id == null || id <= 0) {
            errors.rejectValue("id", "invalid.id", "ID must be a positive integer");
        }
    }

    private void validateCreatedAt(LocalDateTime createdAt, Errors errors) {
        if (createdAt == null || createdAt.isAfter(LocalDateTime.now())) {
            errors.rejectValue("createdAt", "invalid.createdAt", "Creation date must be in the past");
        }
    }

    private void validateUpdatedAt(LocalDateTime updatedAt, Errors errors) {
        if (updatedAt != null && updatedAt.isAfter(LocalDateTime.now())) {
            errors.rejectValue("updatedAt", "invalid.updatedAt", "Update date cannot be in the future");
        }
    }
}
