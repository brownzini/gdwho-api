package com.gdwho.api.application.usecases;

import java.util.List;

import com.gdwho.api.application.gateways.DataGateway;
import com.gdwho.api.domain.shape.EntriesDomainShape;

public class DataUseCase {

    private final DataGateway dataGateway;

    public DataUseCase(DataGateway dataGateway) {
        this.dataGateway = dataGateway;
    }

    public void createData(String response, List<String> dataList, List<EntriesDomainShape> entries, Long userId) {
        dataGateway.createData(response, dataList, entries, userId);
    }

}
