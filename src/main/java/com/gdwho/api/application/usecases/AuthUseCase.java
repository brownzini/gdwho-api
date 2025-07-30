package com.gdwho.api.application.usecases;

import com.gdwho.api.application.gateways.AuthGateway;
import com.gdwho.api.domain.entities.auth.AuthDomainEntity;

public class AuthUseCase {

    private final AuthGateway authGateway;

    public AuthUseCase(AuthGateway authGateway) {
        this.authGateway = authGateway;
    }

    public String login(String username, String password) {
        return authGateway.login(username, password);
    }

    public AuthDomainEntity register(String username, String password) {
        return authGateway.register(username, password);
    }
    
}
