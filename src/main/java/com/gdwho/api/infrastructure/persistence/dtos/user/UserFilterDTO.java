package com.gdwho.api.infrastructure.persistence.dtos.user;

import java.time.Instant;

import com.gdwho.api.domain.entities.user.RoleEnum;

public record UserFilterDTO(String username, RoleEnum role, Instant createdAfter) {
}
