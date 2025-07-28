package com.gdwho.api.infrastructure.controllers.auth.dtos.response;

import java.time.Instant;

import com.gdwho.api.domain.entities.user.RoleEnum;

public record RegisterAuthResponseDTO(String username, RoleEnum role, Instant createdAfter) {

}
