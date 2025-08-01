package com.gdwho.api.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.gdwho.api.application.gateways.ModelApiGateway;
import com.gdwho.api.application.usecases.ModelApiUseCase;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ModelApiUseCaseTest {

    @Test
    @DisplayName("Should return training result from gateway")
    void shouldReturnTrainingResult() {
       
        ModelApiGateway mockGateway = mock(ModelApiGateway.class);
        ModelApiUseCase useCase = new ModelApiUseCase(mockGateway);

        Long userId = 1L;
        List<EntriesDomainEntity> mockData = List.of(
            new EntriesDomainEntity(1L, "input", "output", 0.9)
        );
        String expectedResult = "Training complete";

        when(mockGateway.train(userId, mockData)).thenReturn(expectedResult);

        String result = useCase.train(userId, mockData);

        assertEquals(expectedResult, result);
        verify(mockGateway, times(1)).train(userId, mockData);
    }

    @Test
    @DisplayName("Should return guess result from gateway")
    void shouldReturnGuessResult() {

        ModelApiGateway mockGateway = mock(ModelApiGateway.class);
        ModelApiUseCase useCase = new ModelApiUseCase(mockGateway);

        Long userId = 1L;
        List<String> contextData = List.of("data1", "data2");
        String input = "test input";
        double expectedGuess = 0.85;

        when(mockGateway.guess(userId, contextData, input)).thenReturn(expectedGuess);

        double result = useCase.guess(userId, contextData, input);

        assertEquals(expectedGuess, result, 0.001);
        verify(mockGateway, times(1)).guess(userId, contextData, input);
    }
}
