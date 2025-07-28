package com.gdwho.api.application.gateways;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

public interface UserGateway {
    List<UserDomainEntity> getAllUsers(Pageable pageable, UserFilterDomain filter);

    UserDomainEntity findByUsername(String username);
}
