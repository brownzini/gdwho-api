
package com.gdwho.api.infrastructure.gateways.auth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.gdwho.api.application.gateways.AuthGateway;

import com.gdwho.api.infrastructure.controllers.auth.dtos.response.RegisterAuthResponseDTO;
import com.gdwho.api.infrastructure.persistence.dtos.user.UserResponseDTO;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;
import com.gdwho.api.infrastructure.security.JwtUtil;
import com.gdwho.api.infrastructure.security.exceptions.InvalidCredentialsException;

public class AuthImplementation implements AuthGateway {

    private final UserRepository userRepository;
    private final AuthEntityMapper authEntityMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthImplementation(UserRepository userRepository, AuthEntityMapper authEntityMapper) {
        this.userRepository = userRepository;
        this.authEntityMapper = authEntityMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional(readOnly = true)
    @Override
    public String login(String username, String password) {

        UserResponseDTO user = userRepository.findDTOResponseByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        if (passwordEncoder.matches(password, user.password())) {
            String token = JwtUtil.generateToken(username);
            return token;
        } else {
            throw new InvalidCredentialsException();
        }
    }
    
    @Transactional
    @Override
    public RegisterAuthResponseDTO register(String username, String password) {

        UserDBEntity userDBEntity = authEntityMapper.toRegisterUserDBEntity(username, password);

        String encryptedPassword = passwordEncoder.encode(password);
        userDBEntity.setPassword(encryptedPassword);

        UserDBEntity savedDBEntity = userRepository.save(userDBEntity);

        return authEntityMapper.toRegisterUserDomainEntity(savedDBEntity);
    }

}
