package com.example.imageboard.comment.projection;

import java.time.LocalDateTime;

public interface CommentProjection {
    Long getId();
    String getContent();
    String getImageUrl();
    String getAuthorUsername();
    LocalDateTime getCreatedAt();
}
