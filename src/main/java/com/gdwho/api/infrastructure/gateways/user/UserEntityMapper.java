package com.gdwho.api.infrastructure.gateways.user;

import java.time.Instant;
import java.util.List;

import com.gdwho.api.domain.entities.data.DataDomainEntity;
import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.gdwho.api.domain.entities.user.UserDomainEntity;

import com.gdwho.api.infrastructure.persistence.dtos.user.UserPersistenceFilterDTO;
import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.EntriesDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

public class UserEntityMapper {

        UserDBEntity toEntity(UserDomainEntity user) {

                List<DataDBEntity> dataDBEntity = user.dataList().stream()
                                .map(data -> new DataDBEntity(data.value()))
                                .toList();

                List<EntriesDBEntity> entriesDBEntity = user.entries().stream()
                                .map(data -> new EntriesDBEntity(data.input(), data.output(), data.label()))
                                .toList();

                UserDBEntity entity = new UserDBEntity(
                                user.username(),
                                user.password(),
                                user.role(),
                                user.createdAt(),
                                dataDBEntity,
                                entriesDBEntity);

                return entity;
        }

        UserDomainEntity toDomain(UserDBEntity user) {
                return new UserDomainEntity(
                                user.getId(),
                                user.getUsername(),
                                user.getPassword(),
                                user.getRole(),
                                user.getDataResponse(),
                                user.getCreatedAt(),
                                user.getData().stream()
                                                .map(data -> new DataDomainEntity(data.getId(), data.getValue()))
                                                .toList(),
                                user.getEntries().stream()
                                                .map(entrie -> new EntriesDomainEntity(entrie.getId(),
                                                                entrie.getInput(), entrie.getOutput(),
                                                                entrie.getLabel()))
                                                .toList(),
                                null);
        }

        UserPersistenceFilterDTO toFilter(String username, Instant createdAfter) {
                return new UserPersistenceFilterDTO(username, createdAfter);
        }

        public List<UserDomainEntity> toDomainList(List<UserDBEntity> users) {
                return users.stream()
                                .map(user -> new UserDomainEntity(
                                                user.getId(),
                                                user.getUsername(),
                                                user.getPassword(),
                                                user.getRole(),
                                                 user.getDataResponse(),
                                                user.getCreatedAt(),
                                                user.getData().stream()
                                                                .map(data -> new DataDomainEntity(data.getId(),
                                                                                data.getValue()))
                                                                .toList(),
                                                user.getEntries().stream()
                                                                .map(data -> new EntriesDomainEntity(data.getId(),
                                                                                data.getInput(), data.getOutput(),
                                                                                data.getLabel()))
                                                                .toList(),
                                                null))
                                .toList();
        }

}
