package com.gdwho.api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

public class UserDomainEntityTest {

    @Test
    @DisplayName("Should create valid UserDomainEntity")
    void shouldCreateValidUserDomainEntity() {
        UserDomainEntity user = new UserDomainEntity(
                "first-user",
                "senha1234567",
                RoleEnum.USER,
                Instant.now(),
                List.of(),
                List.of());

        assertEquals("first-user", user.username());
        assertEquals("senha1234567", user.password());
        assertEquals(RoleEnum.USER, user.role());
        assertFalse(user.isAdmin());
    }

    @Test
    @DisplayName("Should throw exception when username is blank")
    void shouldThrowWhenUsernameBlank() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new UserDomainEntity(
                        " ",
                        "senha1234567",
                        RoleEnum.USER,
                        Instant.now(),
                        List.of(),
                        List.of()));

        assertEquals("Username cannot be blank", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when password is too short")
    void shouldThrowWhenPasswordTooShort() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new UserDomainEntity(
                        "first-user",
                        "senha1",
                        RoleEnum.USER,
                        Instant.now(),
                        null,
                        null));

        assertEquals("Password must have at least 8 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when password is null")
    void shouldThrowWhenPasswordIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new UserDomainEntity(
                        "first-user",
                        null,
                        RoleEnum.USER,
                        Instant.now(),
                        null,
                        null));

        assertEquals("Password must have at least 8 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Should return true for admin user")
    void shouldReturnTrueWhenUserIsAdmin() {
        UserDomainEntity adminUser = new UserDomainEntity(
                "admin-user",
                "senha1234567",
                RoleEnum.ADMIN,
                Instant.now(),
                null,
                null);

        assertTrue(adminUser.isAdmin());
    }
}
