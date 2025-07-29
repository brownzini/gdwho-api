package com.gdwho.api.infrastructure.gateways.guess;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gdwho.api.application.gateways.GuessGateway;

import com.gdwho.api.infrastructure.gateways.exceptions.UserAlreadyExistsException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserPersistenceException;
import com.gdwho.api.infrastructure.persistence.entities.GuessDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;
import com.gdwho.api.infrastructure.persistence.repositories.GuessRepository;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

public class GuessImplementation implements GuessGateway {

    private final GuessRepository guessRepository;
    private final UserRepository userRepository;
    private final GuessEntityMapper guessEntityMapper;

    public GuessImplementation(GuessRepository guessRepository, GuessEntityMapper guessEntityMapper,
            UserRepository userRepository) {
        this.guessRepository = guessRepository;
        this.userRepository = userRepository;
        this.guessEntityMapper = guessEntityMapper;
    }

    @Override
    public void createGuess(List<String> inputs, Long userId) throws UsernameNotFoundException {
        try {
            
            UserDBEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userId));
            List<GuessDBEntity> guesses = guessEntityMapper.toEntityList(inputs, user);
            guessRepository.saveAll(guesses);

        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException
                    && ex.getMessage().toLowerCase().contains("unique")) {
                throw new UserAlreadyExistsException("[Invalid input Error]: A input with this value already exists",
                        ex);
            }
            throw new UserPersistenceException("[Persistence Error]: Error persisting the user", ex);
        }

    }

}
