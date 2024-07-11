package com.example.imageboard.role;

import com.example.imageboard.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles") // Assuming you've established a ManyToMany relationship with User
    private List<User> users;

    // Constructor using the role name (for convenience)
    public Role(String name) {
        this.name = name;
    }
}
