package com.gdwho.api.infrastructure.controllers.user;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gdwho.api.application.usecases.GuessUseCase;
import com.gdwho.api.application.usecases.ModelApiUseCase;
import com.gdwho.api.application.usecases.UserUseCase;
import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;
import com.gdwho.api.infrastructure.controllers.user.dtos.UserDTOMapper;
import com.gdwho.api.infrastructure.controllers.user.dtos.request.CreateGuessRequestDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.request.GetAllUsersRequestDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.CreateGuessResponseDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.GetAllUsersResponseDTO;
import com.gdwho.api.infrastructure.controllers.user.dtos.response.SendGuessResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/users")
public class UserController {

  private final UserUseCase userUseCase;
  private final GuessUseCase guessUseCase;
  private final UserDTOMapper userDTOMapper;
  private final ModelApiUseCase modelApiUseCase;

  public UserController(UserUseCase userUseCase,
      UserDTOMapper userDTOMapper, GuessUseCase guessUseCase, ModelApiUseCase modelApiUseCase) {
    this.userUseCase = userUseCase;
    this.userDTOMapper = userDTOMapper;
    this.guessUseCase = guessUseCase;
    this.modelApiUseCase = modelApiUseCase;
  }

  @GetMapping
  public List<GetAllUsersResponseDTO> getAllUsers(Pageable pageable,
      @Valid @ModelAttribute GetAllUsersRequestDTO filter) {

    UserFilterDomain filterBusinessObj = userDTOMapper.toFilterDomain(filter);
    List<UserDomainEntity> usersList = userUseCase.getAllUsers(pageable, filterBusinessObj);

    return userDTOMapper.toGetAllUserResponse(usersList);
  }

  @PostMapping("/{id}/add/guess")
  public ResponseEntity<CreateGuessResponseDTO> createGuess(@Valid @RequestBody CreateGuessRequestDTO request,
      @PathVariable Long id) {
    guessUseCase.createGuess(request.response(), request.inputs(), id);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(id).toUri();
    return ResponseEntity.created(uri).body(new CreateGuessResponseDTO("created"));
  }

  @GetMapping("/{id}/send/guess")
  public SendGuessResponseDTO sendGuess(@RequestParam String input, @PathVariable Long id) {
    BigDecimal apiResponse = modelApiUseCase.send(id, input);
    return userDTOMapper.toSendGuessResponse(apiResponse);
  }

}
