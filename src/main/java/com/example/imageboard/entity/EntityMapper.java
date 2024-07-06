package com.example.imageboard.entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class EntityMapper<E, D> {

    @NonNull
    private final ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(EntityMapper.class); // For logging

    protected D mapEntityToDto(E entity) {
        return Optional.ofNullable(entity)
                .map(e -> modelMapper.map(e, (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]))
                .orElseThrow(() -> {
                    logger.warn("Attempting to map null entity");
                    return new EntityNotFoundException("Entity cannot be null");
                });
    }

    protected E mapDtoToEntity(D dto) {
        return Optional.ofNullable(dto)
                .map(d -> modelMapper.map(d, (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]))
                .orElseThrow(() -> {
                    logger.warn("Attempting to map null DTO");
                    return new EntityNotFoundException("DTO cannot be null");
                });
    }

    protected List<D> mapEntitiesToDtos(List<E> entities) {
        return entities.stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());
    }

    protected List<E> mapDtosToEntities(List<D> dtos) {
        return dtos.stream()
                .map(this::mapDtoToEntity)
                .collect(Collectors.toList());
    }
}

