package com.gdwho.api.domain.entities.auth;

public record AuthDomainEntity(Long id, String username, String password) {
    public AuthDomainEntity {
        if (username == null || username.isBlank() || username.length() < 2 || username.length() > 30) {
            throw new IllegalArgumentException("Username must have between 2 and 30 characters");
        }

        if (password == null || password.isBlank() || password.length() < 8 || password.length() > 100) {
            throw new IllegalArgumentException("Password must have between 8 and 100 characters");
        }
    }
}
