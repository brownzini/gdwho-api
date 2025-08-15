package com.gdwho.api.infrastructure.controllers.game.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateDataResponseRequestDTO(
        @NotBlank(message = "Value not be blank")
        @Size(min = 2, max = 50, message = "field size must be between 2 and 50 characters") 
        String response
) {

}
