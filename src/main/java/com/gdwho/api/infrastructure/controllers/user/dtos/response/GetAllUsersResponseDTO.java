package com.gdwho.api.infrastructure.controllers.user.dtos.response;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.data.DataDomainEntity;

public record GetAllUsersResponseDTO(String username, String password, String dataResponse, Instant createdAt, List<DataDomainEntity> dataList) {

}
