package com.example.imageboard.entity.implementation;

import com.example.imageboard.entity.EntityController;
import com.example.imageboard.entity.EntityDto;
import com.example.imageboard.entity.InvalidEntityException;
import com.example.imageboard.error.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;
import java.util.List;

public class EntityControllerImpl implements EntityController {
    @Override
    public ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<ErrorResponseDto> handleInvalidEntity(InvalidEntityException ex, WebRequest request) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public EntityDto getById(Serializable serializable) {
        return null;
    }

    @Override
    public EntityDto create(EntityDto dto) {
        return null;
    }

    @Override
    public EntityDto update(Serializable serializable, EntityDto dto) {
        return null;
    }

    @Override
    public void delete(Serializable serializable) {

    }
}
