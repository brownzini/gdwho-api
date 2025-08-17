package com.gdwho.api.infrastructure.gateways.auth;

import java.time.Instant;

import com.gdwho.api.domain.entities.auth.AuthDomainEntity;
import com.gdwho.api.domain.entities.data.DataDomainEntity;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

public class AuthEntityMapper {

    UserDBEntity toRegisterUserDBEntity(String username, String password) {
        return new UserDBEntity(username, password, RoleEnum.USER, Instant.now(), null, null);
    }

    AuthDomainEntity toRegisterUserDomainEntity(UserDBEntity user) {
        return new AuthDomainEntity(user.getId(), user.getUsername(), user.getPassword());
    }

    UserDomainEntity toUserDomain(UserDBEntity user, Long userId, String username, String token) {
        return new UserDomainEntity(
                userId,
                username,
                user.getPassword(),
                user.getRole(),
                user.getDataResponse(),
                null,
                user.getData().stream()
                        .map(data -> new DataDomainEntity(data.getId(), data.getValue()))
                        .toList(),
                user.getEntries().stream()
                        .map(entrie -> new EntriesDomainEntity(entrie.getId(), entrie.getInput(), entrie.getOutput(),
                                entrie.getLabel()))
                        .toList(),
                token);
    }
}
