package com.gdwho.api.integration.usecase;

import com.gdwho.api.application.gateways.AuthGateway;
import com.gdwho.api.application.usecases.AuthUseCase;
import com.gdwho.api.domain.entities.auth.AuthDomainEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthUseCaseTest {

    private AuthGateway authGateway;
    private AuthUseCase authUseCase;

    @BeforeEach
    void setUp() {
        authGateway = mock(AuthGateway.class);
        authUseCase = new AuthUseCase(authGateway);
    }

    @Test
    void testLoginReturnsToken() {
        String username = "brownzini";
        String password = "senha123";
        String expectedToken = "jwt-mocked";

        when(authGateway.login(username, password)).thenReturn(expectedToken);

        String result = authUseCase.login(username, password);

        assertEquals(expectedToken, result);
        verify(authGateway).login(username, password);
    }

    @Test
    void testRegisterReturnsAuthDomainEntity() {
        String username = "brownzini";
        String password = "senha123";
        AuthDomainEntity expected = new AuthDomainEntity(1L, username, password);

        when(authGateway.register(username, password)).thenReturn(expected);

        AuthDomainEntity result = authUseCase.register(username, password);

        assertNotNull(result);
        assertEquals(expected.id(), result.id());
        assertEquals(expected.username(), result.username());
        assertEquals(expected.password(), result.password());
        verify(authGateway).register(username, password);
    }
}

