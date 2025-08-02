package com.gdwho.api.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Pageable;

import com.gdwho.api.application.gateways.UserGateway;
import com.gdwho.api.application.usecases.UserUseCase;
import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    @DisplayName("Expect list of UserDomainEntity from getAllUsers return")
    void shouldReturnListOfUsersFromGateway() {

        Pageable pageable = Pageable.unpaged();
        UserFilterDomain filter = new UserFilterDomain(null, null);
        List<UserDomainEntity> mockUsers = List.of(
                new UserDomainEntity(1L,"first-user", "password123457", RoleEnum.ADMIN, Instant.now(), null, null),
                new UserDomainEntity(2L,"second-user", "password123457", RoleEnum.USER, Instant.now(), null, null));

        when(userGateway.getAllUsers(pageable, filter)).thenReturn(mockUsers);

        List<UserDomainEntity> result = userUseCase.getAllUsers(pageable, filter);

        assertEquals(mockUsers, result);
        verify(userGateway, times(1)).getAllUsers(pageable, filter);
    }

    @Test
    @DisplayName("Expect error in getAllUsers return")
    void shouldThrowExceptionWhenGatewayFails() {
        Pageable pageable = Pageable.unpaged();
        UserFilterDomain filter = new UserFilterDomain(null, null);

        RuntimeException expectedException = new RuntimeException("Database error");

        when(userGateway.getAllUsers(pageable, filter)).thenThrow(expectedException);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            userUseCase.getAllUsers(pageable, filter);
        });

        assertEquals("Database error", thrown.getMessage());
        verify(userGateway, times(1)).getAllUsers(pageable, filter);
    }

    @Test
    @DisplayName("Expect UserDomainEntity from findByUsername return")
    void shouldReturnUserFromGateway() {

        String username = "first-user";

        UserDomainEntity mockUser = new UserDomainEntity(1L,"first-user", "password123457", RoleEnum.ADMIN, Instant.now(),
                null, null);

        when(userGateway.findByUsername(username)).thenReturn(mockUser);

        UserDomainEntity result = userUseCase.findByUsername(username);

        assertEquals(mockUser, result);
        verify(userGateway, times(1)).findByUsername(username);
    }

    @Test
    @DisplayName("Should throw exception when user is not found")
    void shouldThrowExceptionWhenUserNotFound() {

        String username = "nonexistent-user";

        when(userGateway.findByUsername(username))
                .thenThrow(new NoSuchElementException("User not found"));

        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> userUseCase.findByUsername(username));

        assertEquals("User not found", exception.getMessage());
        verify(userGateway, times(1)).findByUsername(username);
    }

}
