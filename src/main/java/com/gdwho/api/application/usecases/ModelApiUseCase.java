package com.gdwho.api.application.usecases;

import java.util.List;

import com.gdwho.api.application.gateways.ModelApiGateway;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

public class ModelApiUseCase {

    private final ModelApiGateway modelApiGateway;

    public ModelApiUseCase(ModelApiGateway modelApiGateway) {
        this.modelApiGateway = modelApiGateway;
    }

    public String train(Long id, List<EntriesDomainEntity> data) {
        return modelApiGateway.train(id, data);
    }

    public double guess(Long id, List<String> data, String input) {
        return modelApiGateway.guess(id, data, input);
    }

}
