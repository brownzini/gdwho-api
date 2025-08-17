package com.gdwho.api.application.gateways;

import com.gdwho.api.domain.entities.auth.AuthDomainEntity;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

public interface AuthGateway {
    UserDomainEntity login(Long userId, String username, String password, String oldToken);
    AuthDomainEntity register(String username, String password);
}
