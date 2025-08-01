package com.gdwho.api.domain.entities.entries;

public record EntriesDomainEntity(Long id, String input, String output, double label) {
    public EntriesDomainEntity {

        if (input == null || input.isBlank() || input.length() < 2 || input.length() > 100) {
            throw new IllegalArgumentException("Input must have between 2 and 100 characters");
        }

        if (output == null || output.isBlank() || output.length() < 2 || output.length() > 100) {
            throw new IllegalArgumentException("Output must have between 2 and 100 characters");
        }

        if (label < 0.00 || label > 1.00) { 
            throw new IllegalArgumentException("Label must be between 0.00 and 1.00");
        }
    }
}

