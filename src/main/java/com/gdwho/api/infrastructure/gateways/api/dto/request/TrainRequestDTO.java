package com.gdwho.api.infrastructure.gateways.api.dto.request;

import java.util.List;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

public record TrainRequestDTO(
    Long id,
    List<EntriesDomainEntity> entries
) { 
}
