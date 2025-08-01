package com.gdwho.api.application.usecases;

import java.util.List;

import com.gdwho.api.application.gateways.GameGateway;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.github.fge.jsonpatch.JsonPatch;

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

    public void dataUpdate(Long dataId, Long userId, String value) {
        gameGateway.dataUpdate(dataId, userId, value);
    }

    public void entrieUpdate(Long dataId, Long userId, JsonPatch patch) {
        gameGateway.entrieUpdate(dataId, userId, patch);
    }

    public void deleteData(Long dataId, Long userId ) {
        gameGateway.deleteData(dataId, userId);
    }

    public void deleteEntrie(Long entrieId, Long userId) {
        gameGateway.deleteEntrie(entrieId, userId);
    }
}
