package com.gdwho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdwho.api.application.gateways.GameGateway;

import com.gdwho.api.application.usecases.GameUseCase;
import com.gdwho.api.application.usecases.ModelApiUseCase;

import com.gdwho.api.infrastructure.gateways.game.GameEntityMapper;
import com.gdwho.api.infrastructure.gateways.game.GameImplementation;
import com.gdwho.api.infrastructure.persistence.repositories.DataRepository;
import com.gdwho.api.infrastructure.persistence.repositories.EntriesRepository;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

@Configuration
public class GameConfig {

    @Bean
    GameUseCase createDataCase(GameGateway gameGateway) {
        return new GameUseCase(gameGateway);
    }

    @Bean
    GameGateway gameGateway(DataRepository dataRepository, EntriesRepository entriesRepository,
            GameEntityMapper gameEntityMapper,
            UserRepository userRepository, ModelApiUseCase modelApiUseCase) {
        return new GameImplementation(dataRepository, entriesRepository, gameEntityMapper, userRepository,
                modelApiUseCase);
    }

    @Bean
    GameEntityMapper gameEntityMapper() {
        return new GameEntityMapper();
    }
}