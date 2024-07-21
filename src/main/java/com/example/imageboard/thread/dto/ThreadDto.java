package com.example.imageboard.thread.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThreadDto {

    private Long id;
    private String title;
    private Long forumId;
    private String content;
    private String url;
}


