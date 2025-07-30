package com.gdwho.api.domain.entities.user;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.data.DataDomainEntity;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

public record UserDomainEntity(String username, String password, RoleEnum role, String dataResponse, Instant createdAt, List<DataDomainEntity> dataList, List<EntriesDomainEntity> entries) {
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
