package com.gdwho.api.domain.entities.auth;

public record AuthDomainEntity(Long id, String username, String password) {
    public AuthDomainEntity {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must have at least 8 characters");
        }
    }
}
