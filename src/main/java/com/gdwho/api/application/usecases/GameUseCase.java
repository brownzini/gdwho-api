package com.gdwho.api.application.usecases;

import java.util.List;

import com.gdwho.api.application.gateways.GameGateway;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

public class GameUseCase {

    private final GameGateway gameGateway;

    public GameUseCase(GameGateway gameGateway) {
        this.gameGateway = gameGateway;
    }

    public void createGame(String response, List<String> dataList, List<EntriesDomainEntity> entries, Long userId) {
        gameGateway.createGame(response, dataList, entries, userId);
    }

    public double guessResult(String input, Long userId) {
        return gameGateway.guessResult(input, userId);
    }

    public void partialUpdateData(Long dataId, String value) {
        gameGateway.partialUpdateData(dataId, value);
    }
}
