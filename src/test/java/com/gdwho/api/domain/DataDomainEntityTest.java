package com.gdwho.api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gdwho.api.domain.entities.data.DataDomainEntity;

public class DataDomainEntityTest {

    @Test
    void shouldCreateValidDataDomainEntity() {
        Long id = 1L;
        String value = "validValue";
        DataDomainEntity data = new DataDomainEntity(id, value);

        assertEquals(id, data.id());
        assertEquals(value, data.value());
    }

    @Test
    void shouldThrowWhenValueIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DataDomainEntity(1L, null);
        });
        assertEquals("Value must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenValueIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DataDomainEntity(1L, "  ");
        });
        assertEquals("Value must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenValueTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DataDomainEntity(1L, "a");
        });
        assertEquals("Value must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenValueTooLong() {
        String longValue = "a".repeat(101);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DataDomainEntity(1L, longValue);
        });
        assertEquals("Value must have between 2 and 100 characters", exception.getMessage());
    }
}