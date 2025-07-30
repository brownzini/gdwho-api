package com.gdwho.api.application.gateways;

import java.math.BigDecimal;

public interface ModelApiGateway {
    public BigDecimal send(Long id, String input);
}
