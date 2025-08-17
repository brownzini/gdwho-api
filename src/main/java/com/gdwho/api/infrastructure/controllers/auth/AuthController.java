package com.gdwho.api.infrastructure.controllers.auth;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gdwho.api.application.usecases.AuthUseCase;
import com.gdwho.api.domain.entities.auth.AuthDomainEntity;
import com.gdwho.api.domain.entities.user.UserDomainEntity;
import com.gdwho.api.infrastructure.controllers.auth.dtos.AuthDTOMapper;
import com.gdwho.api.infrastructure.controllers.auth.dtos.request.LoginAuthRequestDTO;
import com.gdwho.api.infrastructure.controllers.auth.dtos.request.RegisterAuthRequestDTO;
import com.gdwho.api.infrastructure.controllers.auth.dtos.response.LoginAuthResponseDTO;
import com.gdwho.api.infrastructure.controllers.auth.dtos.response.RegisterAuthResponseDTO;
import com.gdwho.api.infrastructure.security.exceptions.UnauthorizedException;
import com.gdwho.api.infrastructure.security.jwt.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthController {

    private final AuthUseCase authUseCase;
    private final AuthDTOMapper authDTOMapper;
    private final JwtUtil jwtUtil;

    public AuthController(AuthUseCase authUseCase, AuthDTOMapper authDTOMapper, JwtUtil jwtUtil) {
        this.authUseCase = authUseCase;
        this.authDTOMapper = authDTOMapper;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginAuthResponseDTO login(
            @Nullable @RequestHeader("Authorization") String authorizationHeader,
            @Valid @RequestBody LoginAuthRequestDTO request) {

        if (isBearerTokenValid(authorizationHeader)) {
            String token = extractToken(authorizationHeader);
            Long userId = jwtUtil.extractUserId(token);

            UserDomainEntity userData = authUseCase.login(userId, null, null, token);
            return authDTOMapper.toLoginAuthResponse(userData);
        }

        UserDomainEntity userData = authUseCase.login(null, request.username(), request.password(), null);
        return authDTOMapper.toLoginAuthResponse(userData);
    }

    private boolean isBearerTokenValid(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = extractToken(authorizationHeader);
        if (!jwtUtil.validateToken(token)) {
            throw new UnauthorizedException("[Session Error]: Your session is invalid");
        }

        return true;
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring(7);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterAuthResponseDTO> register(@Valid @RequestBody RegisterAuthRequestDTO request) {
        AuthDomainEntity authenticadeUser = authUseCase.register(request.username(), request.password());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(authenticadeUser.id()).toUri();
        RegisterAuthResponseDTO responseDTO = authDTOMapper.toRegisterAuthUserResponse(authenticadeUser);
        return ResponseEntity.created(uri).body(responseDTO);
    }

}