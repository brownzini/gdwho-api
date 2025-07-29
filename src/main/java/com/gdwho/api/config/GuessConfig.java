package com.gdwho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdwho.api.application.gateways.GuessGateway;
import com.gdwho.api.application.usecases.GuessUseCase;

import com.gdwho.api.infrastructure.gateways.guess.GuessEntityMapper;
import com.gdwho.api.infrastructure.gateways.guess.GuessImplementation;
import com.gdwho.api.infrastructure.persistence.repositories.GuessRepository;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

@Configuration
public class GuessConfig {

    @Bean
    GuessUseCase createGuessCase(GuessGateway guessGateway) {
        return new GuessUseCase(guessGateway);
    }

    @Bean
    GuessGateway guessGateway(GuessRepository guessRepository, UserRepository userRepository, GuessEntityMapper guessEntityMapper) {
        return new GuessImplementation(guessRepository, guessEntityMapper, userRepository);
    }

    @Bean
    GuessEntityMapper guessEntityMapper() {
        return new GuessEntityMapper();
    }
}
