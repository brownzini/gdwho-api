package com.gdwho.api.application.gateways;

import com.gdwho.api.infrastructure.controllers.auth.dtos.response.RegisterAuthResponseDTO;

public interface AuthGateway {
    String login(String username, String password);
    RegisterAuthResponseDTO register(String username, String password);
}
