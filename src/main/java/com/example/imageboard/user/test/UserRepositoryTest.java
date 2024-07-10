package com.example.imageboard.user.test;

import com.example.imageboard.user.User;
import com.example.imageboard.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest  // Use @DataJpaTest for repository-level tests
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername_ExistingUser() {
        User user = User.builder().username("existinguser").email("existinguser@example.com").password("password").build();
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("existinguser");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get()).isEqualTo(user);
    }

    // ... other test cases
}

