package com.gdwho.api.infrastructure.gateways.user;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.gdwho.api.application.gateways.UserGateway;
import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.domain.entities.user.UserDomainEntity;
import com.gdwho.api.infrastructure.persistence.dtos.user.UserPersistenceFilterDTO;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;
import com.gdwho.api.infrastructure.persistence.repositories.UserRepository;
import com.gdwho.api.infrastructure.persistence.specification.UserSpecifications;

public class UserImplementation implements UserGateway {

  private final UserRepository userRepository;
  private final UserEntityMapper userEntityMapper;

  public UserImplementation(UserRepository userRepository, UserEntityMapper userEntityMapper) {
    this.userRepository = userRepository;
    this.userEntityMapper = userEntityMapper;
  }

  @Transactional(readOnly = true)
  @Override
  public UserDomainEntity findByUsername(String username) throws UsernameNotFoundException {
    UserDBEntity userDBEntity = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    return userEntityMapper.toDomain(userDBEntity);
  }

  @Transactional(readOnly = true)
  @Override
  public List<UserDomainEntity> getAllUsers(Pageable pageable, UserFilterDomain filter) {

    UserPersistenceFilterDTO filterQuery = userEntityMapper.toFilter(filter.username(), filter.createdAfter());
    Specification<UserDBEntity> spec = UserSpecifications.withFilters(filterQuery);

    List<UserDBEntity> userDBEntity = userRepository.findAll(spec, pageable).getContent();

    return userEntityMapper.toDomainList(userDBEntity);
  }

}
