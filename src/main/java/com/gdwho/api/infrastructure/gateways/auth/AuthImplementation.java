
package com.gdwho.api.infrastructure.gateways.auth;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.gdwho.api.application.gateways.AuthGateway;
import com.gdwho.api.domain.entities.auth.AuthDomainEntity;

import com.gdwho.api.infrastructure.gateways.exceptions.UserAlreadyExistsException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserPersistenceException;
import com.gdwho.api.infrastructure.persistence.dtos.user.UserPersistenceResponseDTO;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;
import com.gdwho.api.infrastructure.security.exceptions.InvalidCredentialsException;
import com.gdwho.api.infrastructure.security.jwt.JwtUtil;

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

        UserPersistenceResponseDTO user = userRepository.findPasswordAndIdByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("[Not found Error]: User not found: " + username));

        if (passwordEncoder.matches(password, user.password())) {
            String token = JwtUtil.generateToken(username, user.id(), user.role());
            return token;
        } else {
            throw new InvalidCredentialsException();
        }
    }

    @Transactional
    @Override
    public AuthDomainEntity register(String username, String password) {

        try {

            UserDBEntity userDBEntity = authEntityMapper.toRegisterUserDBEntity(username, password);

            String encryptedPassword = passwordEncoder.encode(password);
            userDBEntity.setPassword(encryptedPassword);

            UserDBEntity savedDBEntity = userRepository.save(userDBEntity);

            return authEntityMapper.toRegisterUserDomainEntity(savedDBEntity);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException
                    && ex.getMessage().toLowerCase().contains("unique")) {
                throw new UserAlreadyExistsException("[Invalid user Error]: A user with this username already exists", ex);
            }
            throw new UserPersistenceException("[Persistence Error]: Error persisting the user", ex);
        }
    }

}
