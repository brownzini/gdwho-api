package com.gdwho.api.domain.entities.user;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.guess.GuessDomainEntity;

public record UserDomainEntity(String username, String password, RoleEnum role, Instant createdAt, List<GuessDomainEntity> guess) {
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
