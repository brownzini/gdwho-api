package com.gdwho.api.integration.usecase;

import com.gdwho.api.application.gateways.AuthGateway;
import com.gdwho.api.application.usecases.AuthUseCase;
import com.gdwho.api.domain.entities.auth.AuthDomainEntity;

import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.domain.entities.user.UserDomainEntity;
import com.gdwho.api.infrastructure.controllers.auth.dtos.AuthDTOMapper;
import com.gdwho.api.infrastructure.controllers.auth.dtos.response.LoginAuthResponseDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthUseCaseTest {

    private AuthGateway mockGateway;
    private AuthUseCase authUseCase;
    private AuthDTOMapper authDTOMapper;

    @BeforeEach
    void setUp() {
        mockGateway = mock(AuthGateway.class); 
        authUseCase = new AuthUseCase(mockGateway);
        authDTOMapper = new AuthDTOMapper();
    }

    @Test
    @DisplayName("Should return LoginAuthResponseDTO from login")
    void shouldReturnLoginAuthResponseDTOFromLogin() {

        String username = "brownzini";
        String password = "Senha123";
        String expectedToken = "jwt-mocked";

        // Criação de objetos do domínio
        UserDomainEntity mockedUser = new UserDomainEntity(
                1L, username, password, RoleEnum.ADMIN, null, null, null, null, expectedToken);

        // Aqui usamos o mock corretamente
        when(mockGateway.login(null, username, password, null)).thenReturn(mockedUser);

        // Act
        UserDomainEntity userDomain = authUseCase.login(null, username, password, null);
        LoginAuthResponseDTO responseDTO = authDTOMapper.toLoginAuthResponse(userDomain);

        // Assert
        assertEquals(username, responseDTO.username());
        assertEquals(expectedToken, responseDTO.token());
        verify(mockGateway, times(1)).login(null, username, password, null);
    }

    @Test
    @DisplayName("Should return AuthDomainEntity when register is successful")
    void testRegisterReturnsAuthDomainEntity() {
        // Arrange
        String username = "brownzini";
        String password = "senha123";
        AuthDomainEntity expectedEntity = new AuthDomainEntity(1L, username, password);

        when(mockGateway.register(username, password)).thenReturn(expectedEntity);

        // Act
        AuthDomainEntity actualEntity = authUseCase.register(username, password);

        // Assert
        assertNotNull(actualEntity);
        assertEquals(expectedEntity.id(), actualEntity.id());
        assertEquals(expectedEntity.username(), actualEntity.username());
        assertEquals(expectedEntity.password(), actualEntity.password());
        verify(mockGateway, times(1)).register(username, password);
    }
}
