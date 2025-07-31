package com.gdwho.api.infrastructure.controllers.user;

import java.util.List;

import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdwho.api.application.usecases.UserUseCase;
import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

import com.gdwho.api.infrastructure.controllers.user.dtos.UserDTOMapper;

import com.gdwho.api.infrastructure.controllers.user.dtos.request.GetAllUsersRequestDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.GetAllUsersResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

  private final UserUseCase userUseCase;
  private final UserDTOMapper userDTOMapper;

  public UserController(UserUseCase userUseCase,
      UserDTOMapper userDTOMapper) {
    this.userUseCase = userUseCase;
    this.userDTOMapper = userDTOMapper;
  }

  @GetMapping
  public List<GetAllUsersResponseDTO> getAllUsers(Pageable pageable,
      @Valid @ModelAttribute GetAllUsersRequestDTO filter) {

    UserFilterDomain filterBusinessObj = userDTOMapper.toFilterDomain(filter);
    List<UserDomainEntity> usersList = userUseCase.getAllUsers(pageable, filterBusinessObj);

    return userDTOMapper.toGetAllUserResponse(usersList);
  }

}
