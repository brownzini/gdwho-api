package com.gdwho.api.application.gateways;

import java.util.List;

import com.gdwho.api.domain.shape.EntriesDomainShape;

public interface ModelApiGateway {
    public String train(Long id, List<EntriesDomainShape> data);
    public double guess(Long id, List<String> data, String input);
}
