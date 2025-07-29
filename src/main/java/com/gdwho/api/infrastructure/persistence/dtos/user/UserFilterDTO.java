package com.gdwho.api.infrastructure.persistence.dtos.user;

import java.time.Instant;

public record UserFilterDTO(String username, Instant createdAfter) {
}
