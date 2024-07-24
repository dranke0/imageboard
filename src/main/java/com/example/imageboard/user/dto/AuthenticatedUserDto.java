package com.example.imageboard.user.dto;

import com.example.imageboard.status.Status;
import com.example.imageboard.user.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticatedUserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
    private Status status;
    private Role role;
}