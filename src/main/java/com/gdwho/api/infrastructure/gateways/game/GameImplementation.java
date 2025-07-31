package com.gdwho.api.infrastructure.gateways.game;

import java.util.List;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdwho.api.application.gateways.GameGateway;
import com.gdwho.api.application.usecases.ModelApiUseCase;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;

import com.gdwho.api.infrastructure.gateways.exceptions.DataNotFoundException;
import com.gdwho.api.infrastructure.gateways.exceptions.EntrieNotFoundException;
import com.gdwho.api.infrastructure.gateways.exceptions.FieldNotFoundException;
import com.gdwho.api.infrastructure.gateways.exceptions.NoValidFieldException;
import com.gdwho.api.infrastructure.gateways.exceptions.NotPossibleTrainModelException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserAlreadyExistsException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserPersistenceException;
import com.gdwho.api.infrastructure.persistence.dtos.entries.EntriesPersistenceDTO;
import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.EntriesDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;
import com.gdwho.api.infrastructure.persistence.repositories.DataRepository;
import com.gdwho.api.infrastructure.persistence.repositories.EntriesRepository;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

import org.springframework.transaction.annotation.Transactional;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.function.Consumer;

public class GameImplementation implements GameGateway {

    private final DataRepository dataRepository;
    private final EntriesRepository entriesRepository;
    private final UserRepository userRepository;
    private final GameEntityMapper gameEntityMapper;
    private final ModelApiUseCase modelApiUseCase;
    private final ObjectMapper objectMapper;

    public GameImplementation(DataRepository dataRepository, EntriesRepository entriesRepository,
            GameEntityMapper gameEntityMapper,
            UserRepository userRepository, ModelApiUseCase modelApiUseCase, ObjectMapper objectMapper) {
        this.dataRepository = dataRepository;
        this.entriesRepository = entriesRepository;
        this.userRepository = userRepository;
        this.gameEntityMapper = gameEntityMapper;
        this.modelApiUseCase = modelApiUseCase;
        this.objectMapper = objectMapper;
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

    @Transactional
    @Override
    public void dataUpdate(Long dataId, String value) {
        boolean dataExists = dataRepository.existsById(dataId);
        if (dataExists) {
            dataRepository.updateValueById(dataId, value);
        } else {
            throw new DataNotFoundException("[Error Data]: Data not found");
        }
    }

    @Transactional
    @Override
    public void entrieUpdate(Long entrieId, JsonPatch patch) {
        try {
            boolean entriesExists = entriesRepository.existsById(entrieId);
            if (entriesExists) {

                JsonNode dataNode = objectMapper.convertValue(new EntriesPersistenceDTO(null, null,null), JsonNode.class);
                JsonNode patchedNode = patch.apply(dataNode);

                EntriesPersistenceDTO patchedEntrie = objectMapper.treeToValue(patchedNode,
                        EntriesPersistenceDTO.class);

                entrieBusinessRulesValidation(patchedEntrie);

                entriesRepository.updateEntrieById(entrieId, patchedEntrie.input(), patchedEntrie.output(),
                        patchedEntrie.label());

            } else {
                throw new EntrieNotFoundException("[Entrie Error]: Entrie not found");
            }
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new FieldNotFoundException("[Error Field]: Field not found");
        }
    }

    private void entrieBusinessRulesValidation(EntriesPersistenceDTO entrie) {
        validateField(entrie.input(), "input");
        validateField(entrie.output(), "output");
        validateField(entrie.label(), "label");
    }

    private void validateField(Object value, String fieldName) {
        if (value == null)
            return;

        Consumer<Object> validator = VALIDATION_RULES.get(fieldName);
        if (validator == null) {
            throw new NoValidFieldException("[Invalid Field]: One or more invalid fields");
        }

        validator.accept(value);
    }

    private static final Map<String, Consumer<Object>> VALIDATION_RULES = Map.of(
            "input", value -> {
                String stringValue = (String) value;
                if (stringValue.isBlank() || stringValue.length() < 2 || stringValue.length() > 100) {
                    throw new NoValidFieldException(
                            "[Input Error]: It must be between 2 and 100 characters long and must not be empty");
                }
            },
            "output", value -> {
                String stringValue = (String) value;
                if (stringValue.isBlank() || stringValue.length() < 2 || stringValue.length() > 100) {
                    throw new NoValidFieldException(
                            "[Output Error]: It must be between 2 and 100 characters long and must not be empty");
                }
            },
            "label", value -> {
                double doubleValue = (Double) value;
                if (doubleValue < 0.00 || doubleValue > 1.00) {
                    throw new NoValidFieldException("[Label Error]: It should be between 0.00 and 1.00");
                }
            });

}
