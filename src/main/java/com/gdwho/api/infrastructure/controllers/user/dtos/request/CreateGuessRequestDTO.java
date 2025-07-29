package com.gdwho.api.infrastructure.controllers.user.dtos.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateGuessRequestDTO(
    @Size(min = 2, max = 100, message = "field size must be between 2 and 100 characters") String response,
    @NotEmpty List<String> inputs
) {
}
