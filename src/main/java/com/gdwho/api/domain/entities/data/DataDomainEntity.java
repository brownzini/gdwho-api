package com.gdwho.api.domain.entities.data;

public record DataDomainEntity(Long id, String value) {
    public DataDomainEntity {

        if (value == null || value.isBlank() || value.length() < 2 || value.length() > 100) {
            throw new IllegalArgumentException("Value must have between 2 and 100 characters");
        }

    }
}