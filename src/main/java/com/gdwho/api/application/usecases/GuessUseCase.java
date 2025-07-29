package com.gdwho.api.application.usecases;

import java.util.List;

import com.gdwho.api.application.gateways.GuessGateway;

public class GuessUseCase {

    private final GuessGateway guessGateway;

    public GuessUseCase(GuessGateway guessGateway) {
        this.guessGateway = guessGateway;
    }

    public void createGuess(String response, List<String> inputs, Long userId) {
        guessGateway.createGuess(response, inputs, userId);
    }

}
