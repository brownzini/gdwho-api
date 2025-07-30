
package com.gdwho.api.infrastructure.controllers.user.dtos;

import java.util.List;

import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

import com.gdwho.api.infrastructure.controllers.user.dtos.request.GetAllUsersRequestDTO;

import com.gdwho.api.infrastructure.controllers.user.dtos.response.GetAllUsersResponseDTO;

public class UserDTOMapper {

  public UserFilterDomain toFilterDomain(GetAllUsersRequestDTO request) {
    return new UserFilterDomain(request.username(), request.createdAfter());
  }

  public List<GetAllUsersResponseDTO> toGetAllUserResponse(List<UserDomainEntity> usersList) {
    return usersList.stream()
        .map(user -> new GetAllUsersResponseDTO(
            user.username(),
            user.password(),
            user.dataResponse(),
            user.createdAt(),
            user.dataList(),
            user.entries()
        ))
        .toList();
  }


}
