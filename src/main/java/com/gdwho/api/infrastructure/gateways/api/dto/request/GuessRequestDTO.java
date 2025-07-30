package com.gdwho.api.infrastructure.gateways.api.dto.request;

import java.util.List;

public record GuessRequestDTO(
    Long id,
    List<String> data,
    String input
) { 
}
