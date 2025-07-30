package com.gdwho.api.infrastructure.gateways.api.dto.request;

import java.util.List;

import com.gdwho.api.domain.shape.EntriesDomainShape;

public record TrainRequestDTO(
    Long id,
    List<EntriesDomainShape> entries
) { 
}
