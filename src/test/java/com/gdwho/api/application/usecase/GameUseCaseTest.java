package com.gdwho.api.application.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import com.gdwho.api.application.gateways.GameGateway;
import com.gdwho.api.application.usecases.GameUseCase;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.gdwho.api.domain.entities.user.RoleEnum;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public class GameUseCaseTest {

    private GameGateway gameGateway;
    private GameUseCase gameUseCase;

    @BeforeEach
    void setup() {
        gameGateway = mock(GameGateway.class);
        gameUseCase = new GameUseCase(gameGateway);
    }

    @Test
    @DisplayName("Should call createGame on gateway")
    void shouldCallCreateGame() {
        String response = "some response";
        List<String> dataList = List.of("data1", "data2");
        List<EntriesDomainEntity> entries = List.of(
                new EntriesDomainEntity(1L, "input", "output", 0.5)
        );
        Long userId = 1L;

        gameUseCase.createGame(response, dataList, entries, userId);

        verify(gameGateway, times(1)).createGame(response, dataList, entries, userId);
    }

    @Test
    @DisplayName("Should call guessResult on gateway and return result")
    void shouldReturnGuessResult() {
        String input = "some input";
        Long userId = 2L;
        double expected = 0.75;

        when(gameGateway.guessResult(input, userId)).thenReturn(expected);

        double result = gameUseCase.guessResult(input, userId);

        verify(gameGateway, times(1)).guessResult(input, userId);
        assert result == expected;
    }

    @Test
    @DisplayName("Should call dataUpdate on gateway")
    void shouldCallDataUpdate() {
        Long dataId = 1L;
        Long userId = 2L;
        RoleEnum role = RoleEnum.ADMIN;
        String value = "updated";

        gameUseCase.dataUpdate(dataId, userId, role, value);

        verify(gameGateway, times(1)).dataUpdate(dataId, userId, role, value);
    }

    @Test
    @DisplayName("Should call entrieUpdate on gateway")
    void shouldCallEntrieUpdate() {
        Long dataId = 1L;
        Long userId = 2L;
        RoleEnum role = RoleEnum.USER;
        JsonPatch patch = mock(JsonPatch.class);

        gameUseCase.entrieUpdate(dataId, userId, role, patch);

        verify(gameGateway, times(1)).entrieUpdate(dataId, userId, role, patch);
    }

    @Test
    @DisplayName("Should call deleteData on gateway")
    void shouldCallDeleteData() {
        Long dataId = 1L;
        Long userId = 2L;
        RoleEnum role = RoleEnum.ADMIN;

        gameUseCase.deleteData(dataId, userId, role);

        verify(gameGateway, times(1)).deleteData(dataId, userId, role);
    }

    @Test
    @DisplayName("Should call deleteEntrie on gateway")
    void shouldCallDeleteEntrie() {
        Long entrieId = 1L;
        Long userId = 2L;
        RoleEnum role = RoleEnum.USER;

        gameUseCase.deleteEntrie(entrieId, userId, role);

        verify(gameGateway, times(1)).deleteEntrie(entrieId, userId, role);
    }
}

