package com.gdwho.api.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.gdwho.api.application.gateways.AuthGateway;
import com.gdwho.api.application.usecases.AuthUseCase;
import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.domain.entities.user.UserDomainEntity;
import com.gdwho.api.infrastructure.controllers.auth.dtos.AuthDTOMapper;
import com.gdwho.api.infrastructure.controllers.auth.dtos.response.LoginAuthResponseDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthUseCaseTest {

    @Test
    @DisplayName("Should return LoginAuthResponseDTO from login")
    void shouldReturnLoginAuthResponseDTOFromLogin() {

        AuthGateway mockGateway = mock(AuthGateway.class);
        
        AuthUseCase authUseCase = new AuthUseCase(mockGateway);
        AuthDTOMapper authDTOMapper = new AuthDTOMapper();

        String username = "user123";
        String password = "password123";
        String expectedToken = "mocked.jwt.token";

        UserDomainEntity mockedUser = new UserDomainEntity(1L, username, password, RoleEnum.ADMIN, "response", null, null, null, expectedToken);
        
        when(mockGateway.login(null, username, password, null)).thenReturn(mockedUser);

        UserDomainEntity userDomain = authUseCase.login(null, username, password, null);
        LoginAuthResponseDTO responseDTO = authDTOMapper.toLoginAuthResponse(userDomain);

        assertEquals(username, responseDTO.username());
        assertEquals(expectedToken, responseDTO.token());
        verify(mockGateway, times(1)).login(null, username, password, null);
    }
}
