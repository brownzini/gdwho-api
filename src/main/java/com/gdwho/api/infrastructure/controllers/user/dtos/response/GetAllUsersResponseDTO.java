package com.gdwho.api.infrastructure.controllers.user.dtos.response;

import java.time.Instant;

import com.gdwho.api.domain.entities.user.RoleEnum;

public record GetAllUsersResponseDTO(String username, String password, RoleEnum role, Instant createdAt) {

}
