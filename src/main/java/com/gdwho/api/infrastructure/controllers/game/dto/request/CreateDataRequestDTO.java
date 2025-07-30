package com.gdwho.api.infrastructure.controllers.game.dto.request;

import java.util.List;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateDataRequestDTO(
        @Size(min = 2, max = 50, message = "field size must be between 2 and 50 characters") String response,
        @NotEmpty List<String> dataList,
        @NotEmpty List<EntriesDomainEntity> entries,
        @NotNull
        Long id
) {
}
