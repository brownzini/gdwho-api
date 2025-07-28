package com.gdwho.api.infrastructure.gateways.user;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.user.RoleEnum;
import com.gdwho.api.domain.entities.user.UserDomainEntity;
import com.gdwho.api.infrastructure.persistence.dtos.user.UserFilterDTO;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

public class UserEntityMapper {

  UserDBEntity toEntity(UserDomainEntity user) {
    return new UserDBEntity(user.username(), user.password(), user.role(), user.createdAt());
  }

  UserDomainEntity toDomain(UserDBEntity user) {
    return new UserDomainEntity(user.getUsername(), user.getPassword(), user.getRole(), user.getCreatedAt());
  }

  UserFilterDTO toFilter(String username, RoleEnum role, Instant createdAfter) {
    return new UserFilterDTO(username, role, createdAfter);
  }

  public List<UserDomainEntity> toDomainList(List<UserDBEntity> users) {
    return users.stream()
        .map(user -> new UserDomainEntity(
            user.getUsername(),
            user.getPassword(),
            user.getRole(),
            user.getCreatedAt()))
        .toList();
  }
  
}
