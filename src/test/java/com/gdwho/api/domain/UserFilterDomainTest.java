package com.gdwho.api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.gdwho.api.domain.entities.filter.UserFilterDomain;

public class UserFilterDomainTest {

    @Test
    @DisplayName("Should allow null values in UserFilterDomain")
    void shouldAllowNullFields() {
        UserFilterDomain filter = new UserFilterDomain(null, null, null);

        assertNull(filter.username());
        assertNull(filter.createdAfter());
    }

    @Test
    @DisplayName("Should create UserFilterDomain with only username")
    void shouldCreateWithOnlyUsername() {
        UserFilterDomain filter = new UserFilterDomain(10L, "onlyUsername", null);

        assertEquals("onlyUsername", filter.username());
        assertNull(filter.createdAfter());
    }

    @Test
    @DisplayName("Should create UserFilterDomain with only createdAfter")
    void shouldCreateWithOnlyCreatedAfter() {
        Instant createdAfter = Instant.parse("2024-01-01T00:00:00Z");
        UserFilterDomain filter = new UserFilterDomain(10L,null, createdAfter);

        assertNull(filter.username());
        assertEquals(createdAfter, filter.createdAfter());
    }
}
