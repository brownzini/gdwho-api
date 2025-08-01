package com.gdwho.api.infrastructure.persistence.dtos.user;

import com.gdwho.api.domain.entities.user.RoleEnum;

public record UserPersistenceResponseDTO(Long id, String password, RoleEnum role) {}
