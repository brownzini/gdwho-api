
package com.gdwho.api.infrastructure.controllers.user.dtos;

import java.math.BigDecimal;
import java.util.List;

import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

import com.gdwho.api.infrastructure.controllers.user.dtos.request.GetAllUsersRequestDTO;

import com.gdwho.api.infrastructure.controllers.user.dtos.response.GetAllUsersResponseDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.SendGuessResponseDTO;

public class UserDTOMapper {

  public UserFilterDomain toFilterDomain(GetAllUsersRequestDTO request) {
    return new UserFilterDomain(request.username(), request.createdAfter());
  }

  public List<GetAllUsersResponseDTO> toGetAllUserResponse(List<UserDomainEntity> usersList) {
    return usersList.stream()
        .map(user -> new GetAllUsersResponseDTO(
            user.username(),
            user.password(),
            user.guessResponse(),
            user.createdAt(),
            user.guess()
        ))
        .toList();
  }

  public SendGuessResponseDTO toSendGuessResponse(BigDecimal result) {
      return new SendGuessResponseDTO(result);
  }

}
