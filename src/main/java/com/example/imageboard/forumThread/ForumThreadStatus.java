package com.example.imageboard.forumThread;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ForumThreadStatus {
    OPEN("Open"),
    LOCKED("Locked"),
    ARCHIVED("Archived");

    private final String displayName;

    ForumThreadStatus(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    @Override
    public String toString() {
        return displayName;
    }
}

