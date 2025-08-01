package com.gdwho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdwho.api.application.gateways.AuthGateway;
import com.gdwho.api.application.usecases.AuthUseCase;
import com.gdwho.api.infrastructure.controllers.auth.dtos.AuthDTOMapper;

import com.gdwho.api.infrastructure.gateways.auth.AuthEntityMapper;
import com.gdwho.api.infrastructure.gateways.auth.AuthImplementation;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;
import com.gdwho.api.infrastructure.security.jwt.JwtUtil;

@Configuration
public class AuthConfig {

    @Bean
    AuthUseCase authUseCase(AuthGateway authGateway) {
        return new AuthUseCase(authGateway);
    }

    @Bean
    AuthGateway authGateway(UserRepository userRepository, AuthEntityMapper authEntityMapper, JwtUtil jwtUtil) {
        return new AuthImplementation(userRepository, authEntityMapper, jwtUtil);
    }

    @Bean
    AuthEntityMapper authEntityMapper() {
        return new AuthEntityMapper();
    }

    @Bean
    AuthDTOMapper authDTOMapper() {
        return new AuthDTOMapper();
    }

}
