package com.example.imageboard.user;

import com.fasterxml.jackson.annotation.JsonInclude; // For optional field handling
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Include only non-null fields in JSON
public class UserDto {
    private Long id;
    private String username;
    // private String email; // Removed due to privacy concerns

    // Added optional fields
    private String avatarUrl;
    private String bio;
    // private int postCount; // Could include the number of comments the user has made
    // private LocalDateTime joinDate; // Could include the registration date
}
