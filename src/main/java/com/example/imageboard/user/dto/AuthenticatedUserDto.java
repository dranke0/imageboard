package com.example.imageboard.user.dto;

import com.example.imageboard.user.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticatedUserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String avatarUrl;
    private UserStatus status;
    private List<String> roles;
}