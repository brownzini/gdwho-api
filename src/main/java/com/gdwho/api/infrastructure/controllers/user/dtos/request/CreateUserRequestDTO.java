package com.gdwho.api.infrastructure.controllers.user.dtos.request;

import com.gdwho.api.domain.entities.user.RoleEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequestDTO(
    @NotBlank(message = "Required field")
    @Size(min = 2, max = 50, message = "field size must be between 2 and 50 characters")
    String username, 
    @Size(min = 10, max = 30, message = "field size must be between 10 and 100 characters")
    String password, 
    RoleEnum role
) {

}
