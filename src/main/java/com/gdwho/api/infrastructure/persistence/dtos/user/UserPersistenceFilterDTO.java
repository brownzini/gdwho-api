package com.gdwho.api.infrastructure.persistence.dtos.user;

import java.time.Instant;

public record UserPersistenceFilterDTO(String username, Instant createdAfter) {
}
