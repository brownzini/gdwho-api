package com.gdwho.api.infrastructure.security.resolvers;

import com.gdwho.api.domain.entities.user.RoleEnum;

public class AuthenticatedUser {
    private final Long id;
    private final RoleEnum role;

    public AuthenticatedUser(Long id, RoleEnum role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public RoleEnum getRole() {
        return role;
    }
}
