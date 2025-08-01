package com.gdwho.api.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.gdwho.api.application.gateways.AuthGateway;
import com.gdwho.api.application.usecases.AuthUseCase;
import com.gdwho.api.domain.entities.auth.AuthDomainEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthUseCaseTest {

    @Test
    @DisplayName("Should return token from login")
    void shouldReturnTokenFromLogin() {
     
        AuthGateway mockGateway = mock(AuthGateway.class);
        AuthUseCase authUseCase = new AuthUseCase(mockGateway);

        String username = "user123";
        String password = "password123";
        String expectedToken = "mocked.jwt.token";

        when(mockGateway.login(username, password)).thenReturn(expectedToken);

        String token = authUseCase.login(username, password);

        assertEquals(expectedToken, token);
        verify(mockGateway, times(1)).login(username, password);
    }

    @Test
    @DisplayName("Should return AuthDomainEntity from register")
    void shouldReturnAuthEntityFromRegister() {
      
        AuthGateway mockGateway = mock(AuthGateway.class);
        AuthUseCase authUseCase = new AuthUseCase(mockGateway);

        String username = "user123";
        String password = "password123";
        AuthDomainEntity expectedEntity = new AuthDomainEntity(1L, username, password);

        when(mockGateway.register(username, password)).thenReturn(expectedEntity);

        AuthDomainEntity result = authUseCase.register(username, password);

        assertEquals(expectedEntity, result);
        verify(mockGateway, times(1)).register(username, password);
    }
}