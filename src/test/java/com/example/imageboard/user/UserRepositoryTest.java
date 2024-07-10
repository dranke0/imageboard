package com.example.imageboard.user;

import com.example.imageboard.role.Role;
import com.example.imageboard.role.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Inject RoleRepository

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static User user;

    @BeforeEach
    void setUp() {
        Role userRole = Role.builder().name("ROLE_USER").build();
        roleRepository.save(userRole); // Save the role before creating the user

        User user = User.builder()
                .username("testuser")
                .password(passwordEncoder.encode("password123"))
                .email("test@example.com")
                .roles(List.of(userRole))
                .status(UserStatus.ACTIVE)
                .avatarUrl("http://example.com/avatar.jpg")
                .build();

        userRepository.save(user);
    }

    @Test
    void shouldSaveAndFetchUser() {
        Optional<User> foundUser = userRepository.findById(user.getId());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
        assertThat(foundUser.get().getEmail()).isEqualTo("test@example.com");
        assertThat(foundUser.get().getRoles().size()).isEqualTo(1);  // Check associated role
        assertThat(foundUser.get().getRoles().get(0).getName()).isEqualTo("ROLE_USER");
    }

    @Test
    void shouldFindByUsername() {
        Optional<User> foundUser = userRepository.findByUsername("testuser");
        assertThat(foundUser).isPresent().get().isEqualTo(user);
    }

    @Test
    void shouldFindByEmail() {
        Optional<User> foundUser = userRepository.findByEmail("test@example.com");
        assertThat(foundUser).isPresent().get().isEqualTo(user);
    }
}
