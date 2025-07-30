package com.gdwho.api.infrastructure.controllers.user;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gdwho.api.application.usecases.DataUseCase;
import com.gdwho.api.application.usecases.ModelApiUseCase;
import com.gdwho.api.application.usecases.UserUseCase;
import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;
import com.gdwho.api.infrastructure.controllers.user.dtos.UserDTOMapper;
import com.gdwho.api.infrastructure.controllers.user.dtos.request.CreateDataRequestDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.request.GetAllUsersRequestDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.CreateDataResponseDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.GetAllUsersResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

  private final UserUseCase userUseCase;
  private final DataUseCase dataUseCase;
  private final UserDTOMapper userDTOMapper;

  public UserController(UserUseCase userUseCase,
      UserDTOMapper userDTOMapper, DataUseCase dataUseCase, ModelApiUseCase modelApiUseCase) {
    this.userUseCase = userUseCase;
    this.userDTOMapper = userDTOMapper;
    this.dataUseCase = dataUseCase;
  }

  @GetMapping
  public List<GetAllUsersResponseDTO> getAllUsers(Pageable pageable,
      @Valid @ModelAttribute GetAllUsersRequestDTO filter) {

    UserFilterDomain filterBusinessObj = userDTOMapper.toFilterDomain(filter);
    List<UserDomainEntity> usersList = userUseCase.getAllUsers(pageable, filterBusinessObj);

    return userDTOMapper.toGetAllUserResponse(usersList);
  }

  @PostMapping("/{id}/add/data")
  public ResponseEntity<CreateDataResponseDTO> createData(@Valid @RequestBody CreateDataRequestDTO request,
      @PathVariable Long id) {
    dataUseCase.createData(request.response(), request.inputs(), id);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(id).toUri();
    return ResponseEntity.created(uri).body(new CreateDataResponseDTO("created"));
  }

}
