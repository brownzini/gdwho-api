package com.gdwho.api.infrastructure.controllers.user.dtos.response;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.guess.GuessDomainEntity;

public record GetAllUsersResponseDTO(String username, String password, Instant createdAt, List<GuessDomainEntity> guess) {

}
