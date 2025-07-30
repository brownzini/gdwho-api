package com.gdwho.api.application.usecases;

import java.util.List;

import com.gdwho.api.application.gateways.DataGateway;

public class DataUseCase {

    private final DataGateway dataGateway;

    public DataUseCase(DataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    public void createData(String response, List<String> inputs, Long userId) {
        dataGateway.createData(response, inputs, userId);
    }

}
