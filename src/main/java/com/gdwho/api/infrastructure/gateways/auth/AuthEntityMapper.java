package com.gdwho.api.infrastructure.gateways.auth;

import java.time.Instant;

import com.gdwho.api.domain.entities.auth.AuthDomainEntity;
import com.gdwho.api.domain.entities.user.RoleEnum;

import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

public class AuthEntityMapper {
    UserDBEntity toRegisterUserDBEntity(String username, String password) {
        return new UserDBEntity(username, password, RoleEnum.USER, null, Instant.now(), null);
    }
    AuthDomainEntity toRegisterUserDomainEntity(UserDBEntity user) {
        return new AuthDomainEntity(user.getId(), user.getUsername(), user.getPassword());
    }
}
