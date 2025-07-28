package com.gdwho.api.domain.entities.filter;

import java.time.Instant;

import com.gdwho.api.domain.entities.user.RoleEnum;

public record UserFilterDomain(String username, RoleEnum role, Instant createdAfter) {}
