package com.example.imageboard.user;

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Builder;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String avatarUrl;
    private UserStatus status;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> authorities;
}
