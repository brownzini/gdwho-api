
package com.gdwho.api.infrastructure.controllers.user.dtos;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;
import com.gdwho.api.infrastructure.controllers.user.dtos.request.CreateUserRequestDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.request.GetAllUsersRequestDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.CreateUserResponseDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.GetAllUsersResponseDTO;

public class UserDTOMapper {

  public CreateUserResponseDTO toResponseCreateUser(UserDomainEntity user) {
    return new CreateUserResponseDTO(user.username(), user.role(), user.createdAt());
  }

  public UserDomainEntity toRequestCreateUser(CreateUserRequestDTO request) {
    return new UserDomainEntity(request.username(), request.password(), request.role(), Instant.now());
  }

  public UserFilterDomain toFilterDomain(GetAllUsersRequestDTO request) {
    return new UserFilterDomain(request.username(), request.role(), request.createdAfter());
  }

  public List<GetAllUsersResponseDTO> toResponseGetAllUser(List<UserDomainEntity> usersList) {
    return usersList.stream()
        .map(user -> new GetAllUsersResponseDTO(
            user.username(),
            user.password(),
            user.role(),
            user.createdAt()))
        .toList();
  }
}
