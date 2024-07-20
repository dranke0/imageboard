package com.example.imageboard.user.dto;

import com.example.imageboard.user.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUserDto {
    // Additional fields for admin-specific information
    private Long id;
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
    private UserStatus status;
    private List<String> roles;
}