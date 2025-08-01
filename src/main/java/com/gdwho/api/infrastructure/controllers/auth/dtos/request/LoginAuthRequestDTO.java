package com.gdwho.api.infrastructure.controllers.auth.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginAuthRequestDTO(
    @NotBlank(message = "Required field")
    @Size(min = 2, max = 30, message = "field size must be between 2 and 30 characters")
    String username, 
    @NotBlank(message = "Required field")
    @Size(min = 8, max = 100, message = "field size must be between 8 and 100 characters")
    String password
) {

}
