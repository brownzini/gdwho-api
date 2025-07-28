package com.gdwho.api.application.usecases;

import com.gdwho.api.application.gateways.UserGateway;

public class UserUseCase {
    private final UserGateway userGateway;

    public UserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

}