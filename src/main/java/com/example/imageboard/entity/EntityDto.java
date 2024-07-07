package com.example.imageboard.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "updatedAt")
@ToString(callSuper = true)
public abstract class EntityDto {

    public Long id;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
