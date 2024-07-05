package com.example.imageboard.base;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
@RequiredArgsConstructor
public class BaseMapper<E, D> {

    protected final ModelMapper modelMapper;

    // Generic mapping method for entity to DTO
    protected D toDto(E entity, Class<D> dtoClass) {
        if (entity == null) {
            throw new EntityNotFoundException("Entity cannot be null");
        }
        return modelMapper.map(entity, dtoClass);
    }

    // Generic mapping method for DTO to entity
    protected E toEntity(D dto, Class<E> entityClass) {
        if (dto == null) {
            throw new EntityNotFoundException("Dto cannot be null");
        }
        return modelMapper.map(dto, entityClass);
    }
}
