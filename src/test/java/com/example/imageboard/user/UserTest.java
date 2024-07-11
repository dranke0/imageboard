package com.example.imageboard.user;

import com.example.imageboard.comment.Comment;
import com.example.imageboard.role.Role;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testBuilder() {
        Role roleUser = Role.builder().name("USER").build();
        Role roleAdmin = Role.builder().name("ADMIN").build();
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now().plusDays(1); // Different from createdAt

        User user = User.builder()
                .id(1L)
                .username("testuser")
                .email("testuser@example.com")
                .password("password123")
                .roles(List.of(roleUser, roleAdmin))
                .status(UserStatus.ACTIVE)
                .avatarUrl("https://example.com/avatar.jpg")
                .comments(List.of(new Comment()))
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        // Assertions
        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(2, user.getRoles().size());
        assertTrue(user.getRoles().contains(roleUser));
        assertTrue(user.getRoles().contains(roleAdmin));
        assertEquals(UserStatus.ACTIVE, user.getStatus());
        assertEquals("https://example.com/avatar.jpg", user.getAvatarUrl());
        assertEquals(1, user.getComments().size()); // Test non-empty comment list
        assertEquals(createdAt, user.getCreatedAt());
        assertEquals(updatedAt, user.getUpdatedAt());
    }

    @Test
    public void testGetAuthorities() {
        Role roleUser = Role.builder().name("USER").build();
        Role roleAdmin = Role.builder().name("ADMIN").build();

        User user = User.builder()
                .roles(List.of(roleUser, roleAdmin))
                .build();

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(2, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ADMIN")));
    }

    @Test
    public void testGetName() {
        User user = User.builder()
                .username("testuser")
                .build();

        assertEquals("testuser", user.getName());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = User.builder().id(1L).build();
        User user2 = User.builder().id(1L).build();
        User user3 = User.builder().id(2L).build();

        assertEquals(user1, user2); // Same ID, should be equal
        assertNotEquals(user1, user3); // Different IDs, should not be equal
        assertEquals(user1.hashCode(), user2.hashCode()); // Same ID, should have same hashCode
        assertNotEquals(user1.hashCode(), user3.hashCode()); // Different IDs, should have different hashCode
    }

    @Test
    public void testToString() {
        User user = User.builder()
                .id(1L)
                .username("testuser")
                .email("testuser@example.com")
                .build();

        String expected = "User(id=1, username=testuser, email=testuser@example.com";
        assertTrue(user.toString().contains(expected)); // Check for expected fields in the toString() output
    }
}

