package com.gdwho.api.infrastructure.gateways.auth;

import java.time.Instant;

import com.gdwho.api.domain.entities.user.RoleEnum;

import com.gdwho.api.infrastructure.controllers.auth.dtos.response.RegisterAuthResponseDTO;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

public class AuthEntityMapper {
    UserDBEntity toRegisterUserDBEntity(String username, String password) {
        return new UserDBEntity(username, password, RoleEnum.ADMIN, Instant.now(), null);
    }
    RegisterAuthResponseDTO toRegisterUserDomainEntity(UserDBEntity user) {
        return new RegisterAuthResponseDTO(user.getUsername(), user.getRole(), user.getCreatedAt());
    }
}
