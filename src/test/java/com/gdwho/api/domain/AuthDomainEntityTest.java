package com.gdwho.api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gdwho.api.domain.entities.auth.AuthDomainEntity;

public class AuthDomainEntityTest {
    @Test
    void shouldCreateValidAuthDomainEntity() {
        Long id = 1L;
        String username = "validUser";
        String password = "senha1234567";

        AuthDomainEntity auth = new AuthDomainEntity(id, username, password);

        assertEquals(id, auth.id());
        assertEquals(username, auth.username());
        assertEquals(password, auth.password());
    }

    @Test
    void shouldThrowWhenUsernameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new AuthDomainEntity(1L, null, "senha1234567"));
        assertEquals("Username must have between 2 and 30 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenUsernameIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new AuthDomainEntity(1L, "   ", "senha1234567"));
        assertEquals("Username must have between 2 and 30 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenUsernameTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new AuthDomainEntity(1L, "a", "senha1234567"));
        assertEquals("Username must have between 2 and 30 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenUsernameTooLong() {
        String longUsername = "a".repeat(31);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new AuthDomainEntity(1L, longUsername, "senha1234567"));
        assertEquals("Username must have between 2 and 30 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenPasswordIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new AuthDomainEntity(1L, "validUser", null));
        assertEquals("Password must have between 8 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenPasswordIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new AuthDomainEntity(1L, "validUser", "  "));
        assertEquals("Password must have between 8 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenPasswordTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new AuthDomainEntity(1L, "validUser", "short"));
        assertEquals("Password must have between 8 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenPasswordTooLong() {
        String longPassword = "a".repeat(101);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new AuthDomainEntity(1L, "validUser", longPassword));
        assertEquals("Password must have between 8 and 100 characters", exception.getMessage());
    }
}
