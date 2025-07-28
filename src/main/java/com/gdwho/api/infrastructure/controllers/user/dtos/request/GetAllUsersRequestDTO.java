package com.gdwho.api.infrastructure.controllers.user.dtos.request;

import java.time.Instant;

import com.gdwho.api.domain.entities.user.RoleEnum;

import jakarta.validation.constraints.Size;

public record GetAllUsersRequestDTO(
        @Size(min = 2, max = 100, message = "field size must be between 10 and 100 characters") String username,
        RoleEnum role,
        Instant createdAfter) {
}
