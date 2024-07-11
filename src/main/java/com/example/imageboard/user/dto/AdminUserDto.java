package com.example.imageboard.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUserDto extends AuthenticatedUserDto {
    // Additional fields for admin-specific information
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
