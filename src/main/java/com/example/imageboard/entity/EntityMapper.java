package com.example.imageboard.entity;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(force = true)
public class EntityMapper<E, D> {

    private final ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(EntityMapper.class);

    protected D mapEntityToDto(E entity) {
        if (entity == null) {
            logger.error("Attempting to map null entity");
            throw new InvalidEntityException("Entity cannot be null");
        }
        return modelMapper.map(entity, (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
    }

    protected E mapDtoToEntity(D dto) {
        if (dto == null) {
            logger.error("Attempting to map null DTO");
            throw new InvalidEntityException("DTO cannot be null");
        }
        return modelMapper.map(dto, (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
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


