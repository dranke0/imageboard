package com.example.imageboard.entity;

import lombok.*;
import java.time.LocalDateTime;

@Getter // Lombok will generate getters for all fields
@Setter // Lombok will generate setters for all fields (if needed)
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor // Generates a constructor with all fields as arguments
public abstract class EntityDto {

    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
