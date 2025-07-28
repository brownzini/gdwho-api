package com.gdwho.api.infrastructure.controllers.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdwho.api.application.usecases.AuthUseCase;
import com.gdwho.api.infrastructure.controllers.auth.dtos.request.LoginAuthRequestDTO;
import com.gdwho.api.infrastructure.controllers.auth.dtos.request.RegisterAuthRequestDTO;
import com.gdwho.api.infrastructure.controllers.auth.dtos.response.LoginAuthResponseDTO;
import com.gdwho.api.infrastructure.controllers.auth.dtos.response.RegisterAuthResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/login")
    public LoginAuthResponseDTO login(@Valid @RequestBody LoginAuthRequestDTO request) {
        return new LoginAuthResponseDTO(authUseCase.login(request.username(), request.password()));
    }

    @PostMapping("/register")
    public RegisterAuthResponseDTO register(@Valid @RequestBody RegisterAuthRequestDTO request) {
        return authUseCase.register(request.username(), request.password());
    }

}