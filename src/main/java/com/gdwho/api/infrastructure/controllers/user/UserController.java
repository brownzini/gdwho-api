package com.gdwho.api.infrastructure.controllers.user;

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

import com.gdwho.api.application.usecases.GuessUseCase;
import com.gdwho.api.application.usecases.UserUseCase;
import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;
import com.gdwho.api.infrastructure.controllers.user.dtos.UserDTOMapper;
import com.gdwho.api.infrastructure.controllers.user.dtos.request.CreateGuessRequestDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.request.GetAllUsersRequestDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.GetAllUsersResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserUseCase userUseCase;
  private final GuessUseCase guessUseCase;
  private final UserDTOMapper userDTOMapper;

  public UserController(UserUseCase userUseCase,
      UserDTOMapper userDTOMapper, GuessUseCase guessUseCase) {
    this.userUseCase = userUseCase;
    this.userDTOMapper = userDTOMapper;
    this.guessUseCase = guessUseCase;
  }

  @GetMapping
  public List<GetAllUsersResponseDTO> getAllUsers(Pageable pageable,
      @Valid @ModelAttribute GetAllUsersRequestDTO filter) {

    UserFilterDomain filterBusinessObj = userDTOMapper.toFilterDomain(filter);
    List<UserDomainEntity> usersList = userUseCase.getAllUsers(pageable, filterBusinessObj);

    return userDTOMapper.toResponseGetAllUser(usersList);
  }

  @PostMapping("/{id}/guesses")
  public ResponseEntity<Void> createGuess(@Valid @RequestBody CreateGuessRequestDTO request, @PathVariable Long id) {
    guessUseCase.createGuess(request.inputs(), id);
    return ResponseEntity.ok().build();
  }

}
