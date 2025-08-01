package com.gdwho.api.integration.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.gdwho.api.application.gateways.RateLimiterGateway;
import com.gdwho.api.application.usecases.RateLimiterInteractor;

class RateLimiterInteractorTest {

    @Test
    void testTryConsumeReturnsTrue() {

        RateLimiterGateway gatewayMock = mock(RateLimiterGateway.class);

        when(gatewayMock.tryConsume("key1")).thenReturn(true);

        RateLimiterInteractor interactor = new RateLimiterInteractor(gatewayMock);

        boolean result = interactor.tryConsume("key1");

        assertTrue(result);

        verify(gatewayMock, times(1)).tryConsume("key1");
    }

    @Test
    void testTryConsumeReturnsFalse() {
        RateLimiterGateway gatewayMock = mock(RateLimiterGateway.class);
        when(gatewayMock.tryConsume("key2")).thenReturn(false);

        RateLimiterInteractor interactor = new RateLimiterInteractor(gatewayMock);
        boolean result = interactor.tryConsume("key2");

        assertFalse(result);
        verify(gatewayMock, times(1)).tryConsume("key2");
    }
}
