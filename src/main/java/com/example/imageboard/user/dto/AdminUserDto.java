package com.example.imageboard.user.dto;

import com.example.imageboard.user.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUserDto extends AuthenticatedUserDto {
    // Additional fields for admin-specific information
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    AdminUserDto(Long id, String username, String email, String password, String avatarUrl, UserStatus status, List<String> roles) {
        super(id, username, email, password, avatarUrl, status, roles);
    }
}