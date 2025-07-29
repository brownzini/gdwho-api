package com.gdwho.api.application.gateways;

import com.gdwho.api.domain.entities.auth.AuthDomainEntity;

public interface AuthGateway {
    String login(String username, String password);
    AuthDomainEntity register(String username, String password);
}
