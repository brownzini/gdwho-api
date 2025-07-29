package com.gdwho.api.domain.entities.filter;

import java.time.Instant;

public record UserFilterDomain(String username, Instant createdAfter) {}
