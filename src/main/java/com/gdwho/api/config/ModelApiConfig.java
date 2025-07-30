package com.gdwho.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdwho.api.application.gateways.ModelApiGateway;
import com.gdwho.api.application.usecases.ModelApiUseCase;
import com.gdwho.api.infrastructure.gateways.api.ModelApiImplementation;
import com.gdwho.api.infrastructure.gateways.api.ModelV1ApiClient;

@Configuration
public class ModelApiConfig {
    
    @Bean
    ModelApiUseCase modelApiUseCase(ModelApiGateway modelApiGateway) {
        return new ModelApiUseCase(modelApiGateway);
    }

    @Bean
    ModelApiGateway modelApiGateway(ModelV1ApiClient client) {
        return new ModelApiImplementation(client);
    }

}
