package com.gdwho.api.infrastructure.gateways.user;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.data.DataDomainEntity;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

import com.gdwho.api.infrastructure.persistence.dtos.user.UserFilterDTO;
import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

public class UserEntityMapper {

  UserDBEntity toEntity(UserDomainEntity user) {

    List<DataDBEntity> listFromData = user.dataList().stream()
        .map(data -> new DataDBEntity(data.value()))
        .toList();

    UserDBEntity entity = new UserDBEntity(
        user.username(),
        user.password(),
        user.role(),
        user.dataResponse(),
        user.createdAt(),
        listFromData);

    return entity;
  }

  UserDomainEntity toDomain(UserDBEntity user) {
    return new UserDomainEntity(
        user.getUsername(),
        user.getPassword(),
        user.getRole(),
        user.getDataResponse(),
        user.getCreatedAt(),
        user.getData().stream()
            .map(data -> new DataDomainEntity(data.getId(), data.getValue()))
            .toList());
  }

  UserFilterDTO toFilter(String username, Instant createdAfter) {
    return new UserFilterDTO(username, createdAfter);
  }

  public List<UserDomainEntity> toDomainList(List<UserDBEntity> users) {
    return users.stream()
        .map(user -> new UserDomainEntity(
            user.getUsername(),
            user.getPassword(),
            user.getRole(),
            user.getDataResponse(),
            user.getCreatedAt(),
            user.getData().stream()
                .map(data -> new DataDomainEntity(data.getId(), data.getValue()))
                .toList()))
        .toList();
  }

}
