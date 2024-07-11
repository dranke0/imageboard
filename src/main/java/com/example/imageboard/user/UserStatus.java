package com.example.imageboard.user;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
    ACTIVE("Active User") {
        @Override
        public boolean canPost() {
            return true;
        }
    },
    INACTIVE("Inactive User") {
        @Override
        public boolean canPost() {
            return false;
        }
    },
    BANNED("Banned User") {
        @Override
        public boolean canPost() {
            return false;
        }
    };

    private final String displayName; // Store a display name for each status

    UserStatus(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue // Use the display name for JSON serialization
    @Override
    public String toString() {
        return displayName;
    }

    public abstract boolean canPost(); // Abstract method to define posting permissions
}

