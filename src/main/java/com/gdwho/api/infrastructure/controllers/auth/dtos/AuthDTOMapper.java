
package com.gdwho.api.infrastructure.controllers.auth.dtos;

import java.time.Instant;

import com.gdwho.api.domain.entities.auth.AuthDomainEntity;
import com.gdwho.api.domain.entities.user.RoleEnum;

import com.gdwho.api.infrastructure.controllers.auth.dtos.response.RegisterAuthResponseDTO;

public class AuthDTOMapper {

  public RegisterAuthResponseDTO toRegisterAuthUserResponse(AuthDomainEntity auth) {
      return new RegisterAuthResponseDTO(auth.id(), auth.username(), RoleEnum.USER, Instant.now());
  }
}
