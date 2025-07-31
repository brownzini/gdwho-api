package com.gdwho.api.application.gateways;

import java.util.List;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

public interface GameGateway {
    void createGame(String response, List<String> dataList, List<EntriesDomainEntity> entries, Long userId);
    double guessResult(String input, Long userId);
    void update(Long dataId, String value);
}
