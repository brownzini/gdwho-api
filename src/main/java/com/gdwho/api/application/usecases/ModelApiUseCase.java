package com.gdwho.api.application.usecases;

import java.math.BigDecimal;

import com.gdwho.api.application.gateways.ModelApiGateway;

public class ModelApiUseCase {

    private final ModelApiGateway modelApiGateway;

    public ModelApiUseCase(ModelApiGateway modelApiGateway) {
        this.modelApiGateway = modelApiGateway;
    }

    public BigDecimal send(Long id, String input) {
        return modelApiGateway.send(id, input);
    }
    
}
