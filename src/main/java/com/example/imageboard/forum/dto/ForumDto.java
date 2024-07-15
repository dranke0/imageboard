package com.example.imageboard.forum.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForumDto {
    private Long id;
    private String name;
    private String description;

    public ForumDto() {

    }

    public ForumDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForumDto forumDto)) return false;
        return Objects.equals(id, forumDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

