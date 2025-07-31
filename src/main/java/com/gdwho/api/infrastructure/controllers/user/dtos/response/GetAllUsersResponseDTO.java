package com.gdwho.api.infrastructure.controllers.user.dtos.response;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.data.DataDomainEntity;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

public record GetAllUsersResponseDTO(String username, String password, Instant createdAt, List<DataDomainEntity> dataList, List<EntriesDomainEntity> entries) {

}
