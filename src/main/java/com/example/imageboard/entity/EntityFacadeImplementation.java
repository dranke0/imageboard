package com.example.imageboard.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class EntityFacadeImplementation<E, D> implements EntityFacade<E, D> {

    private final EntityMapper<E, D> entityMapper;

    @Override
    public D mapEntityToDto(E entity) {
        return entityMapper.mapEntityToDto(entity);
    }

    @Override
    public E mapDtoToEntity(D dto) {
        return entityMapper.mapDtoToEntity(dto);
    }

    @Override
    public List<D> mapEntitiesToDtos(List<E> entities) {
        return entityMapper.mapEntitiesToDtos(entities);
    }

    @Override
    public List<E> mapDtosToEntities(List<D> dtos) {
        return entityMapper.mapDtosToEntities(dtos);
    }

    @Override
    public Optional<D> mapOptionalEntityToDto(Optional<E> entity) {
        return entity.map(this::mapEntityToDto);
    }

    @Override
    public Optional<E> mapOptionalDtoToEntity(Optional<D> dto) {
        return dto.map(this::mapDtoToEntity);
    }
}


