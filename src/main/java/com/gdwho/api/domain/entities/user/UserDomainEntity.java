package com.gdwho.api.domain.entities.user;

import java.time.Instant;

public record UserDomainEntity(String username, String password, RoleEnum role, Instant createdAt) {
    public UserDomainEntity {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }

        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must have at least 8 characters");
        }
    }

    public boolean isAdmin() {
        return RoleEnum.ADMIN.equals(role);
    }
}
