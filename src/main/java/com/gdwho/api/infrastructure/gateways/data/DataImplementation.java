package com.gdwho.api.infrastructure.gateways.data;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gdwho.api.application.gateways.DataGateway;
import com.gdwho.api.application.usecases.ModelApiUseCase;
import com.gdwho.api.domain.shape.EntriesDomainShape;
import com.gdwho.api.infrastructure.gateways.exceptions.NotPossibleTrainModelException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserAlreadyExistsException;
import com.gdwho.api.infrastructure.gateways.exceptions.UserPersistenceException;
import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;
import com.gdwho.api.infrastructure.persistence.repositories.DataRepository;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;

import jakarta.transaction.Transactional;

public class DataImplementation implements DataGateway {

    private final DataRepository dataRepository;
    private final UserRepository userRepository;
    private final DataEntityMapper dataEntityMapper;
    private final ModelApiUseCase modelApiUseCase;

    public DataImplementation(DataRepository dataRepository, DataEntityMapper dataEntityMapper,
            UserRepository userRepository, ModelApiUseCase modelApiUseCase) {
        this.dataRepository = dataRepository;
        this.userRepository = userRepository;
        this.dataEntityMapper = dataEntityMapper;
        this.modelApiUseCase = modelApiUseCase;
    }

    @Transactional
    @Override
    public void createData(String response, List<String> dataList, List<EntriesDomainShape> entries, Long userId)
            throws UsernameNotFoundException {
        try {

            UserDBEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userId));

            List<DataDBEntity> entityDataList = dataEntityMapper.toEntityList(dataList, user);
            user.setDataResponse(response);

            String trainResponse = modelApiUseCase.train(userId, entries);

            if (trainResponse.equals("success")) {
                userRepository.save(user);
                dataRepository.saveAll(entityDataList);
            } else {
                throw new NotPossibleTrainModelException("[Train Error]: Error when training model");
            }

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
