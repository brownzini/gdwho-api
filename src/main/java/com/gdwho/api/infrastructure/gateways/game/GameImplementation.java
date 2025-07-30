package com.gdwho.api.infrastructure.gateways.game;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gdwho.api.application.gateways.GameGateway;
import com.gdwho.api.application.usecases.ModelApiUseCase;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.gdwho.api.infrastructure.gateways.exceptions.NotPossibleTrainModelException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserAlreadyExistsException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserPersistenceException;
import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.EntriesDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;
import com.gdwho.api.infrastructure.persistence.repositories.DataRepository;
import com.gdwho.api.infrastructure.persistence.repositories.EntriesRepository;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;

public class GameImplementation implements GameGateway {

    private final DataRepository dataRepository;
    private final EntriesRepository entriesRepository;
    private final UserRepository userRepository;
    private final GameEntityMapper gameEntityMapper;
    private final ModelApiUseCase modelApiUseCase;

    public GameImplementation(DataRepository dataRepository, EntriesRepository entriesRepository,
            GameEntityMapper gameEntityMapper,
            UserRepository userRepository, ModelApiUseCase modelApiUseCase) {
        this.dataRepository = dataRepository;
        this.entriesRepository = entriesRepository;
        this.userRepository = userRepository;
        this.gameEntityMapper = gameEntityMapper;
        this.modelApiUseCase = modelApiUseCase;
    }

    @Transactional(readOnly = true)
    @Override
    public double guessResult(String input, Long userId) {
        List<String> dataList = dataRepository.findValuesByUserId(userId);
        if (dataList.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + userId);
        }
        return modelApiUseCase.guess(userId, dataList, input);
    }

    @Transactional
    @Override
    public void createGame(String response, List<String> dataList, List<EntriesDomainEntity> entries, Long userId)
            throws UsernameNotFoundException {
        try {

            UserDBEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userId));

            List<DataDBEntity> dataListDBEntity = gameEntityMapper.toDataListDBEntity(dataList, user);
            List<EntriesDBEntity> entriesDBEntity = gameEntityMapper.toEntriesDBEntity(entries, user);
            
            user.setDataResponse(response);
            
            String trainResponse = modelApiUseCase.train(userId, entries);

            if (trainResponse.equals("success")) {
                userRepository.save(user);
                dataRepository.saveAll(dataListDBEntity);
                entriesRepository.saveAll(entriesDBEntity);
            } else {
                throw new NotPossibleTrainModelException("[Train Error]: Error when training model");
            }

        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException
                    && ex.getMessage().toLowerCase().contains("unique")) {
                throw new UserAlreadyExistsException("[Invalid input Error]: A input with this value already exists",
                        ex);
            }
            throw new UserPersistenceException("[Persistence Error]: Error persisting the user data", ex);
        }

    }

}
