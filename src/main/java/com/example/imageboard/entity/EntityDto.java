package com.example.imageboard.entity;

import java.time.LocalDateTime;
public interface EntityDto {
    Long getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
