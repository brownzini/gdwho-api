package com.gdwho.api.infrastructure.controllers.game;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gdwho.api.application.usecases.GameUseCase;
import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.infrastructure.controllers.game.dto.request.CreateDataRequestDTO;
import com.gdwho.api.infrastructure.controllers.game.dto.request.UpdateDataRequestDTO;
import com.gdwho.api.infrastructure.controllers.game.dto.request.UpdateDataResponseRequestDTO;
import com.gdwho.api.infrastructure.controllers.game.dto.response.CreateDataResponseDTO;
import com.gdwho.api.infrastructure.controllers.game.dto.response.GetTotalGamesResponseDTO;
import com.gdwho.api.infrastructure.controllers.game.dto.response.SendGuessResponseDTO;

import com.gdwho.api.infrastructure.security.resolvers.Authenticated;
import com.gdwho.api.infrastructure.security.resolvers.AuthenticatedUser;
import com.github.fge.jsonpatch.JsonPatch;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/game")
public class GameController {

    private final GameUseCase gameUseCase;
    private final GameDTOMapper gameDTOMapper;

    public GameController(GameUseCase gameUseCase, GameDTOMapper gameDTOMapper) {
        this.gameUseCase = gameUseCase;
        this.gameDTOMapper = gameDTOMapper;
    }

    @GetMapping("/{id}/guess")
    public SendGuessResponseDTO sedGuess(@Valid @RequestParam String input,
            @PathVariable Long id) {

        double response = gameUseCase.guessResult(input, id);
        return new SendGuessResponseDTO(response);

    }

    @GetMapping("/search/total")
    public List<GetTotalGamesResponseDTO> search() {
        List<UserFilterDomain> gameList = gameUseCase.getTotal();
        return gameDTOMapper.toResponse(gameList);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateDataResponseDTO> createGame(@Valid @RequestBody CreateDataRequestDTO request) {

        gameUseCase.createGame(request.response(), request.dataList(), request.entries(), request.id());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(request.id()).toUri();

        return ResponseEntity.created(uri).body(new CreateDataResponseDTO("created"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteGame(@Authenticated AuthenticatedUser user) {
        gameUseCase.deleteGame(user.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/response")
    public ResponseEntity<Void> updateResponse(@Authenticated AuthenticatedUser user,
            @Valid @RequestBody UpdateDataResponseRequestDTO request) {

        gameUseCase.responseUpdate(user.getId(), user.getRole(), request.response());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/data/{id}")
    public ResponseEntity<Void> updateData(@Authenticated AuthenticatedUser user,
            @Valid @RequestBody UpdateDataRequestDTO request, @PathVariable Long id) {

        gameUseCase.dataUpdate(id, user.getId(), user.getRole(), request.value());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/update/entrie/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Void> updateEntrie(@Authenticated AuthenticatedUser user, @RequestBody JsonPatch patch,
            @PathVariable Long id) {
        gameUseCase.entrieUpdate(id, user.getId(), user.getRole(), patch);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<Void> deleteData(@Authenticated AuthenticatedUser user, @PathVariable Long id) {
        gameUseCase.deleteData(id, user.getId(), user.getRole());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/entrie/{id}")
    public ResponseEntity<Void> deleteEntrie(@Authenticated AuthenticatedUser user, @PathVariable Long id) {
        gameUseCase.deleteEntrie(id, user.getId(), user.getRole());
        return ResponseEntity.noContent().build();
    }

}
