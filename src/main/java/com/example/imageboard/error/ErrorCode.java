package com.example.imageboard.error;

public enum ErrorCode {
    ENTITY_NOT_FOUND("not_found"),
    INVALID_ENTITY("invalid_entity"),
    INTERNAL_SERVER_ERROR("internal_server_error");

    private final String code; // Associate a string code with each enum value

    ErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}

