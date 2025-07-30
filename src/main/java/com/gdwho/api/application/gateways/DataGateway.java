package com.gdwho.api.application.gateways;

import java.util.List;

import com.gdwho.api.domain.shape.EntriesDomainShape;

public interface DataGateway {
    public void createData(String response, List<String> dataList, List<EntriesDomainShape> entries, Long userId);
}
