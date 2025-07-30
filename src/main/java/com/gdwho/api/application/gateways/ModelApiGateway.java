package com.gdwho.api.application.gateways;

import java.util.List;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

public interface ModelApiGateway {
    String train(Long id, List<EntriesDomainEntity> data);
    double guess(Long id, List<String> data, String input);
}
