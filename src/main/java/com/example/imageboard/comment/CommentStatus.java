package com.example.imageboard.comment;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CommentStatus {
    ACTIVE("Active"),
    DELETED("Deleted"),
    ARCHIVED("Archived");

    private final String displayName;

    CommentStatus(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue // Use the displayName for JSON serialization
    @Override
    public String toString() {
        return displayName;
    }
}
