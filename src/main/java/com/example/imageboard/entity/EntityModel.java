package com.example.imageboard.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface EntityModel {
    Long getId();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}

