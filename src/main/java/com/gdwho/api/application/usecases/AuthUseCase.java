package com.gdwho.api.application.usecases;

import com.gdwho.api.application.gateways.AuthGateway;
import com.gdwho.api.domain.entities.auth.AuthDomainEntity;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

public class AuthUseCase {

    private final AuthGateway authGateway;

    public AuthUseCase(AuthGateway authGateway) {
        this.authGateway = authGateway;
    }

    public UserDomainEntity login(Long userId, String username, String password, String oldToken) {
        return authGateway.login(userId, username, password, oldToken);
    }

    public AuthDomainEntity register(String username, String password) {
        return authGateway.register(username, password);
    }
    
}
