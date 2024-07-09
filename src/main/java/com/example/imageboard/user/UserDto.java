package com.example.imageboard.user;

import com.example.imageboard.entity.EntityDto;
import com.fasterxml.jackson.annotation.JsonInclude; // For optional field handling
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Include only non-null fields in JSON
public class UserDto implements EntityDto {
    private Long id;
    private String username;
    // private String email; // Removed due to privacy concerns
    private String avatarUrl;
    private String bio;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // private int postCount; // Could include the number of comments the userEntity has made
    // private LocalDateTime joinDate; // Could include the registration date

    // Copilot how do I improve this User class
}
