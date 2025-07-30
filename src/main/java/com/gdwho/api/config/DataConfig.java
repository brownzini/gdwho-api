package com.gdwho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdwho.api.application.gateways.DataGateway;
import com.gdwho.api.application.usecases.DataUseCase;
import com.gdwho.api.application.usecases.ModelApiUseCase;

import com.gdwho.api.infrastructure.gateways.data.DataEntityMapper;
import com.gdwho.api.infrastructure.gateways.data.DataImplementation;
import com.gdwho.api.infrastructure.persistence.repositories.DataRepository;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

@Configuration
public class DataConfig {

    @Bean
    DataUseCase createDataCase(DataGateway dataGateway) {
        return new DataUseCase(dataGateway);
    }

    @Bean
    DataGateway dataGateway(DataRepository dataRepository, DataEntityMapper dataEntityMapper,
            UserRepository userRepository, ModelApiUseCase modelApiUseCase) {
        return new DataImplementation(dataRepository, dataEntityMapper, userRepository, modelApiUseCase);
    }

    @Bean
    DataEntityMapper dataEntityMapper() {
        return new DataEntityMapper();
    }
}
