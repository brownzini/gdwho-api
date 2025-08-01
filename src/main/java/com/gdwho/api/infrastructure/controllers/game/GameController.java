package com.gdwho.api.infrastructure.controllers.game;

import java.net.URI;

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

import com.gdwho.api.infrastructure.controllers.game.dto.request.CreateDataRequestDTO;
import com.gdwho.api.infrastructure.controllers.game.dto.request.UpdateDataRequestDTO;
import com.gdwho.api.infrastructure.controllers.game.dto.response.CreateDataResponseDTO;
import com.gdwho.api.infrastructure.controllers.game.dto.response.SendGuessResponseDTO;
import com.github.fge.jsonpatch.JsonPatch;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/game")
public class GameController {

    private final GameUseCase gameUseCase;

    public GameController(GameUseCase gameUseCase) {
        this.gameUseCase = gameUseCase;
    }

    @GetMapping("/{id}/guess")
    public SendGuessResponseDTO sedGuess(@Valid @RequestParam String input,
            @PathVariable Long id) {

        double response = gameUseCase.guessResult(input, id);
        return new SendGuessResponseDTO(response);

    }

    @PostMapping("/create")
    public ResponseEntity<CreateDataResponseDTO> createData(@Valid @RequestBody CreateDataRequestDTO request) {

        gameUseCase.createGame(request.response(), request.dataList(), request.entries(), request.id());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(request.id()).toUri();

        return ResponseEntity.created(uri).body(new CreateDataResponseDTO("created"));
    }

    @PutMapping("/update/data/{id}")
    public ResponseEntity<Void> updateData(@Valid @RequestBody UpdateDataRequestDTO request, @PathVariable Long id) {
        gameUseCase.dataUpdate(id, request.value());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/update/entrie/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Void> updateEntrie(@RequestBody JsonPatch patch, @PathVariable Long id) {
        gameUseCase.entrieUpdate(id, patch);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/data/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        gameUseCase.deleteData(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/entrie/{id}")
    public ResponseEntity<Void> deleteEntrie(@PathVariable Long id) {
        gameUseCase.deleteEntrie(id);
        return ResponseEntity.noContent().build();
    }

}
