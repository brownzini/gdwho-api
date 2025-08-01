package com.gdwho.api.infrastructure.controllers.user.dtos.response;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.data.DataDomainEntity;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.gdwho.api.domain.entities.user.RoleEnum;

public record GetAllUsersResponseDTO(Long id, String username, String password, RoleEnum role, Instant createdAt, List<DataDomainEntity> dataList, List<EntriesDomainEntity> entries) {

}
