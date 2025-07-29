package com.gdwho.api.infrastructure.controllers.user.dtos.request;

import java.time.Instant;

import jakarta.validation.constraints.Size;

public record GetAllUsersRequestDTO(
                @Size(min = 2, max = 30, message = "field size must be between 10 and 30 characters") String username,
                Instant createdAfter) {
}
