package com.gdwho.api.application.gateways;

import java.util.List;

public interface DataGateway {
    public void createData(String response, List<String> inputs, Long userId);
}
