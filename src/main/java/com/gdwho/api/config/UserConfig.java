package com.gdwho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdwho.api.application.gateways.UserGateway;
import com.gdwho.api.application.usecases.UserUseCase;
import com.gdwho.api.infrastructure.controllers.user.dtos.UserDTOMapper;
import com.gdwho.api.infrastructure.gateways.user.UserEntityMapper;
import com.gdwho.api.infrastructure.gateways.user.UserImplementation;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

@Configuration
public class UserConfig {
    
    @Bean
    UserUseCase createUserCase(UserGateway userGateway) {
        return new UserUseCase(userGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserImplementation(userRepository, userEntityMapper);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }
}
