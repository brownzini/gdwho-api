package com.gdwho.api.domain.entities.filter;

import java.time.Instant;

public record UserFilterDomain(Long id, String username, Instant createdAfter) {}
