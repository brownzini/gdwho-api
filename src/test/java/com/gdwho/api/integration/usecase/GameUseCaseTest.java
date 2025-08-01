package com.gdwho.api.integration.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import com.gdwho.api.application.gateways.GameGateway;
import com.gdwho.api.application.usecases.GameUseCase;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.gdwho.api.domain.entities.user.RoleEnum;
import com.github.fge.jsonpatch.JsonPatch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameUseCaseTest {

    private GameGateway gameGateway;
    private GameUseCase gameUseCase;

    @BeforeEach
    void setup() {
        gameGateway = mock(GameGateway.class);
        gameUseCase = new GameUseCase(gameGateway);
    }

    @Test
    void testCreateGame() {
        String response = "response";
        List<String> dataList = List.of("a", "b");
        List<EntriesDomainEntity> entries = List.of(
            new EntriesDomainEntity(1L, "in", "out", 0.5)
        );
        Long userId = 1L;

        gameUseCase.createGame(response, dataList, entries, userId);

        verify(gameGateway, times(1)).createGame(response, dataList, entries, userId);
    }

    @Test
    void testGuessResult() {
        String input = "input";
        Long userId = 42L;
        double expected = 0.88;

        when(gameGateway.guessResult(input, userId)).thenReturn(expected);

        double result = gameUseCase.guessResult(input, userId);

        assertEquals(expected, result, 0.0001);
        verify(gameGateway, times(1)).guessResult(input, userId);
    }

    @Test
    void testDataUpdate() {
        Long dataId = 1L;
        Long userId = 2L;
        RoleEnum role = RoleEnum.USER;
        String value = "new value";

        gameUseCase.dataUpdate(dataId, userId, role, value);

        verify(gameGateway, times(1)).dataUpdate(dataId, userId, role, value);
    }

    @Test
    void testEntrieUpdate() {
        Long dataId = 3L;
        Long userId = 4L;
        RoleEnum role = RoleEnum.ADMIN;
        JsonPatch patch = mock(JsonPatch.class);

        gameUseCase.entrieUpdate(dataId, userId, role, patch);

        verify(gameGateway, times(1)).entrieUpdate(dataId, userId, role, patch);
    }

    @Test
    void testDeleteData() {
        Long dataId = 5L;
        Long userId = 6L;
        RoleEnum role = RoleEnum.USER;

        gameUseCase.deleteData(dataId, userId, role);

        verify(gameGateway, times(1)).deleteData(dataId, userId, role);
    }

    @Test
    void testDeleteEntrie() {
        Long entrieId = 7L;
        Long userId = 8L;
        RoleEnum role = RoleEnum.ADMIN;

        gameUseCase.deleteEntrie(entrieId, userId, role);

        verify(gameGateway, times(1)).deleteEntrie(entrieId, userId, role);
    }
}
