package com.gdwho.api.infrastructure.controllers.user.dtos.response;

import java.time.Instant;

import com.gdwho.api.domain.entities.user.RoleEnum;

public record CreateUserResponseDTO(String username, RoleEnum role, Instant createdAt) {

}
