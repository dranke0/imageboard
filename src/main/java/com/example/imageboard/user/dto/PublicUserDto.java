package com.example.imageboard.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublicUserDto {
    private Long id;
    private String username;
    private String avatarUrl;
}

