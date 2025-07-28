package com.gdwho.api.application.usecases;

import java.util.List;

import com.gdwho.api.application.gateways.UserGateway;
import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

import org.springframework.data.domain.Pageable;

public class UserUseCase {
    private final UserGateway userGateway;

    public UserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public List<UserDomainEntity> getAllUsers(Pageable pageable, UserFilterDomain filter) {
        return userGateway.getAllUsers(pageable, filter);
    }

}