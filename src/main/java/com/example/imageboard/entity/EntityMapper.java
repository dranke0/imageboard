package com.example.imageboard.entity;

import java.util.List;
import java.util.Optional;

public interface EntityMapper {
    <D extends EntityDto, M extends EntityModel> M toModel(D dto);
    <D extends EntityDto, M extends EntityModel> D toDto(M model);
    <D extends EntityDto, M extends EntityModel> Optional<M> toModel(Optional<D> dto);
    <D extends EntityDto, M extends EntityModel> Optional<D> toDto(Optional<M> model);
    <D extends EntityDto, M extends EntityModel> List<M> toModelList(List<D> dtos);
    <D extends EntityDto, M extends EntityModel> List<D> toDtoList(List<M> models);
}
