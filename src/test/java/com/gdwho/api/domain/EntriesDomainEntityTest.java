package com.gdwho.api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

public class EntriesDomainEntityTest {

    @Test
    void shouldCreateValidEntriesDomainEntity() {
        Long id = 1L;
        EntriesDomainEntity entry = new EntriesDomainEntity(id, "inputValue", "outputValue", 0.5);

        assertEquals(id, entry.id());
        assertEquals("inputValue", entry.input());
        assertEquals("outputValue", entry.output());
        assertEquals(0.5, entry.label());
    }

    @Test
    void shouldThrowWhenInputIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, null, "outputValue", 0.5);
        });
        assertEquals("Input must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenInputIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, " ", "outputValue", 0.5);
        });
        assertEquals("Input must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenInputTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "i", "outputValue", 0.5);
        });
        assertEquals("Input must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenInputTooLong() {
        String longInput = "i".repeat(101);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, longInput, "outputValue", 0.5);
        });
        assertEquals("Input must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenOutputIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", null, 0.5);
        });
        assertEquals("Output must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenOutputTooShort() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", "o", 0.5);
        });
        assertEquals("Output must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenOutputTooLong() {
        String longOutput = "o".repeat(101);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", longOutput, 0.5);
        });
        assertEquals("Output must have between 2 and 100 characters", exception.getMessage());
    }

    @Test
    void shouldThrowWhenLabelBelowZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", "outputValue", -0.1);
        });
        assertEquals("Label must be between 0.00 and 1.00", exception.getMessage());
    }

    @Test
    void shouldThrowWhenLabelAboveOne() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new EntriesDomainEntity(1L, "inputValue", "outputValue", 1.1);
        });
        assertEquals("Label must be between 0.00 and 1.00", exception.getMessage());
    }
}