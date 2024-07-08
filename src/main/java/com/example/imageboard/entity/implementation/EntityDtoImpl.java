package com.example.imageboard.entity.implementation;

import com.example.imageboard.entity.EntityDto;

import java.time.LocalDateTime;

import lombok.*;
@Data
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(exclude = "updatedAt")
@ToString(callSuper = true)
public class EntityDtoImpl implements EntityDto {
    protected final Long id;
    protected final LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
