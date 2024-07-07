package com.example.imageboard.entity;

import com.example.imageboard.error.ErrorCode;
import com.example.imageboard.error.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
@NoArgsConstructor(force = true)
public abstract class EntityController<T extends BaseEntity, ID extends Serializable, D extends EntityDto, S extends EntityService<T, ID, D>> {

    protected final EntityService<T, ID, D> entityService;

    public EntityController(@Qualifier("entityService") EntityService<T, ID, D> entityService) {
        this.entityService = entityService;
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        log.error("Entity not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        ErrorCode.ENTITY_NOT_FOUND.toString(),
                        ex.getMessage(),
                        List.of(),
                        LocalDateTime.now(),
                        request.getDescription(false)
                ));
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidEntity(InvalidEntityException ex, WebRequest request) {
        log.error("Invalid entity: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(
                        HttpStatus.BAD_REQUEST.value(),
                        ErrorCode.INVALID_ENTITY.toString(),
                        "Validation failed",
                        ex.getValidationErrors(),
                        LocalDateTime.now(),
                        request.getDescription(false)
                ));
    }

    // Generic methods for CRUD operations

    @GetMapping
    public List<D> getAll() {
        return entityService.findAllDtos();
    }

    @GetMapping("/{id}")
    public D getById(@PathVariable ID id) {
        return entityService.findByIdDto(id);
    }

    @PostMapping
    public D create(@RequestBody D dto) {
        return entityService.save(dto);
    }

    @PutMapping("/{id}")
    public D update(@PathVariable ID id, @RequestBody D dto) {
        // Add logic to ensure dto.id matches path variable id
        return entityService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) {
        entityService.delete(id);
    }
}


