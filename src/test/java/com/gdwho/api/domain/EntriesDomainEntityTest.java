package com.gdwho.api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

public class EntriesDomainEntityTest {
    @Test
    void shouldCreateValidEntriesDomainEntity() {
        EntriesDomainEntity entry = new EntriesDomainEntity(1L, "inputValue", "outputValue", 0.5);
        assertEquals("inputValue", entry.input());
        assertEquals("outputValue", entry.output());
        assertEquals(0.5, entry.label());
    }

    @Test
    void shouldThrowIfInputIsNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, null, "outputValue", 0.5);
        });
        assertEquals("Input must have between 2 and 100 characters", ex.getMessage());
    }

    @Test
    void shouldThrowIfInputIsBlank() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, " ", "outputValue", 0.5);
        });
        assertEquals("Input must have between 2 and 100 characters", ex.getMessage());
    }

    @Test
    void shouldThrowIfInputTooShort() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "a", "outputValue", 0.5);
        });
        assertEquals("Input must have between 2 and 100 characters", ex.getMessage());
    }

    @Test
    void shouldThrowIfInputTooLong() {
        String longInput = "a".repeat(101);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, longInput, "outputValue", 0.5);
        });
        assertEquals("Input must have between 2 and 100 characters", ex.getMessage());
    }

    @Test
    void shouldThrowIfOutputIsNull() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", null, 0.5);
        });
        assertEquals("Output must have between 2 and 100 characters", ex.getMessage());
    }

    @Test
    void shouldThrowIfOutputIsBlank() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", " ", 0.5);
        });
        assertEquals("Output must have between 2 and 100 characters", ex.getMessage());
    }

    @Test
    void shouldThrowIfOutputTooShort() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", "a", 0.5);
        });
        assertEquals("Output must have between 2 and 100 characters", ex.getMessage());
    }

    @Test
    void shouldThrowIfOutputTooLong() {
        String longOutput = "a".repeat(101);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", longOutput, 0.5);
        });
        assertEquals("Output must have between 2 and 100 characters", ex.getMessage());
    }

    @Test
    void shouldThrowIfLabelTooLow() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", "outputValue", -0.01);
        });
        assertEquals("Label must be between 0.00 and 1.00", ex.getMessage());
    }

    @Test
    void shouldThrowIfLabelTooHigh() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", "outputValue", 1.01);
        });
        assertEquals("Label must be between 0.00 and 1.00", ex.getMessage());
    }
}
