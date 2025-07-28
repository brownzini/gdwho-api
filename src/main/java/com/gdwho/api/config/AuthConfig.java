package com.gdwho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdwho.api.application.gateways.AuthGateway;
import com.gdwho.api.application.usecases.AuthUseCase;
import com.gdwho.api.infrastructure.gateways.auth.AuthEntityMapper;
import com.gdwho.api.infrastructure.gateways.auth.AuthImplementation;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

@Configuration
public class AuthConfig {

    @Bean
    AuthUseCase authUseCase(AuthGateway authGateway) {
        return new AuthUseCase(authGateway);
    }

    @Bean
    AuthGateway authGateway(UserRepository userRepository, AuthEntityMapper authEntityMapper) {
        return new AuthImplementation(userRepository, authEntityMapper);
    }

    @Bean
    AuthEntityMapper authEntityMapper() {
        return new AuthEntityMapper();
    }

}
