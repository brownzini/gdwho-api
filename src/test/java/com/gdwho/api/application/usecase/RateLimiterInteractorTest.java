package com.gdwho.api.application.usecase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.gdwho.api.application.gateways.RateLimiterGateway;
import com.gdwho.api.application.usecases.RateLimiterInteractor;

public class RateLimiterInteractorTest {

    @Test
    @DisplayName("Should allow request when rate limiter accepts")
    void shouldReturnTrueWhenRateLimiterAllows() {
 
        RateLimiterGateway mockGateway = mock(RateLimiterGateway.class);
        String key = "user123|/auth/login";

        when(mockGateway.tryConsume(key)).thenReturn(true);

        RateLimiterInteractor interactor = new RateLimiterInteractor(mockGateway);

        boolean result = interactor.tryConsume(key);

        assertTrue(result);
        verify(mockGateway, times(1)).tryConsume(key);
    }

    @Test
    @DisplayName("Should block request when rate limiter rejects")
    void shouldReturnFalseWhenRateLimiterBlocks() {

        RateLimiterGateway mockGateway = mock(RateLimiterGateway.class);
        String key = "user123|/auth/login";

        when(mockGateway.tryConsume(key)).thenReturn(false);

        RateLimiterInteractor interactor = new RateLimiterInteractor(mockGateway);

        boolean result = interactor.tryConsume(key);

        assertFalse(result);
        verify(mockGateway, times(1)).tryConsume(key);
    }
}