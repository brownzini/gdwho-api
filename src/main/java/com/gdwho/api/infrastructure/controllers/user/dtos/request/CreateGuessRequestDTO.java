package com.gdwho.api.infrastructure.controllers.user.dtos.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public record CreateGuessRequestDTO(@NotEmpty List<String> inputs) {
}
