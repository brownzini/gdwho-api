package com.gdwho.api.infrastructure.controllers.auth.dtos.response;

import java.util.List;

import com.gdwho.api.domain.entities.data.DataDomainEntity;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.gdwho.api.domain.entities.user.RoleEnum;

public record LoginAuthResponseDTO(Long id, String username, RoleEnum role, String response, List<DataDomainEntity> dataList, List<EntriesDomainEntity> entries, String token) {

}
