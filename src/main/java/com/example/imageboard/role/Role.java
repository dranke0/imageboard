package com.example.imageboard.role;

import com.example.imageboard.user.UserForumRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity // Mark this class as a JPA entity
@Table(name = "roles") // Map it to the "roles" table
@Data // Generate getters, setters, equals, hashCode, toString using Lombok
@Builder // Enable builder pattern for creating Role objects
@AllArgsConstructor // Generate a constructor with all arguments
@NoArgsConstructor // Generate a no-argument constructor
@EntityListeners(AuditingEntityListener.class) // Enable auditing (optional)
public class Role {

    @Id // Mark this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false, unique = true) // Unique and non-null constraint
    private String name;

    @Column(nullable = false) // Non-null constraint
    private String description;

    @Builder.Default
    @ElementCollection // Use @ElementCollection to represent a collection of basic values or embeddable objects
    @CollectionTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id")) // Create a separate table for the permissions
    @Enumerated(EnumType.STRING) // Store the enum values as strings in the database
    private List<Permission> permissions = new ArrayList<>(); // Initialize the permissions list

    @Builder.Default
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserForumRole> userForumRoles = new ArrayList<>();
}
