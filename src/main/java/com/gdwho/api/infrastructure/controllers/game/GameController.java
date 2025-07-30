package com.gdwho.api.infrastructure.controllers.game;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdwho.api.application.usecases.DataUseCase;

import com.gdwho.api.infrastructure.controllers.game.dto.response.SendGuessResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/game")
public class GameController {

    private final DataUseCase dataUseCase;

    public GameController(DataUseCase dataUseCase) {
        this.dataUseCase = dataUseCase;
    }

    @GetMapping("/{id}/guess")
    public SendGuessResponseDTO guess(@Valid @RequestParam String input,
            @PathVariable Long id) {

      double response = dataUseCase.guessResult(input, id);
      return new SendGuessResponseDTO(response);
      
    }

}
