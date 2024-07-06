package com.example.imageboard.base;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component // This will make it a Spring component
public class BaseFacadeImpl<E, D> {

    private final BaseMapper<E, D> baseMapper;

    public BaseFacadeImpl(BaseMapper<E, D> baseMapper) {
        this.baseMapper = baseMapper;
    }

    public D mapEntityToDto(E entity) {
        return baseMapper.mapEntityToDto(entity);
    }

    public E mapDtoToEntity(D dto) {
        return baseMapper.mapDtoToEntity(dto);
    }

    public List<D> mapEntitiesToDto(List<E> entities) {
        return baseMapper.mapEntitiesToDtos(entities);
    }

    public List<E> mapDtosToEntity(List<D> dtos) {
        return baseMapper.mapDtosToEntities(dtos);
    }

    public Optional<D> mapEntityToDto(Optional<E> entity) {
        return entity.map(this::mapEntityToDto);
    }

    public Optional<E> mapDtoToEntity(Optional<D> dto) {
        return dto.map(this::mapDtoToEntity);
    }
}

