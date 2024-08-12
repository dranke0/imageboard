package com.example.imageboard.status;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) // Enforce the use of the builder pattern for construction
@NoArgsConstructor(access = AccessLevel.PROTECTED) // For JPA
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    // A static map to hold all instances by name (assuming name is unique and case-insensitive)
    private static final Map<String, Status> STATUSES = new HashMap<>();

    @PostLoad
    @PostPersist
    @PostUpdate
    private void addToStatuses() {
        STATUSES.put(this.name.toUpperCase(), this);
    }

    public static Status valueOf(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        Status status = STATUSES.get(name.toUpperCase());
        if (status == null) {
            throw new IllegalArgumentException("No status found with name: " + name);
        }
        return status;
    }
}

