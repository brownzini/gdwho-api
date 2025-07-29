package com.gdwho.api.application.gateways;

import java.util.List;

public interface GuessGateway {
    public void createGuess(String response, List<String> inputs, Long userId);
}
