package com.gdwho.api.integration.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.gdwho.api.application.gateways.ModelApiGateway;
import com.gdwho.api.application.usecases.ModelApiUseCase;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

class ModelApiUseCaseTest {

    @Test
    void testTrainReturnsExpectedString() {

        ModelApiGateway gatewayMock = mock(ModelApiGateway.class);

        Long id = 1L;
        List<EntriesDomainEntity> data = List.of(
            new EntriesDomainEntity(1L, "input1", "output1", 0.9),
            new EntriesDomainEntity(2L, "input2", "output2", 0.8)
        );

        String expectedResponse = "success";

        when(gatewayMock.train(id, data)).thenReturn(expectedResponse);

        ModelApiUseCase useCase = new ModelApiUseCase(gatewayMock);

        String result = useCase.train(id, data);

        assertEquals(expectedResponse, result);
        verify(gatewayMock, times(1)).train(id, data);
    }

    @Test
    void testGuessReturnsExpectedDouble() {
        ModelApiGateway gatewayMock = mock(ModelApiGateway.class);

        Long id = 1L;
        List<String> data = List.of("input1", "input2");
        String input = "some input";

        double expectedGuess = 0.75;

        when(gatewayMock.guess(id, data, input)).thenReturn(expectedGuess);

        ModelApiUseCase useCase = new ModelApiUseCase(gatewayMock);

        double result = useCase.guess(id, data, input);

        assertEquals(expectedGuess, result, 0.0001);
        verify(gatewayMock, times(1)).guess(id, data, input);
    }
}
