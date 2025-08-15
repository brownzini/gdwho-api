package com.gdwho.api.infrastructure.gateways.game;

import java.util.List;

import com.gdwho.api.domain.entities.entries.EntriesDomainEntity;
import com.gdwho.api.domain.entities.filter.UserFilterDomain;
import com.gdwho.api.infrastructure.persistence.dtos.user.ListUserWithGameResponseDTO;
import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.EntriesDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

public class GameEntityMapper {

  List<DataDBEntity> toDataListDBEntity(List<String> dataListDomainEntity, UserDBEntity userDBEntity) {
    return dataListDomainEntity.stream()
        .map(dataElement -> {
          DataDBEntity data = new DataDBEntity(dataElement);
          data.setUser(userDBEntity);
          return data;
        })
        .toList();
  }

  List<EntriesDBEntity> toEntriesDBEntity(List<EntriesDomainEntity> entriesDomainEntity, UserDBEntity userDBEntity) {
    return entriesDomainEntity.stream()
        .map(entrie -> {
          EntriesDBEntity data = new EntriesDBEntity(entrie.input(), entrie.output(), entrie.label());
          data.setUser(userDBEntity);
          return data;
        })
        .toList();
  }

  List<UserFilterDomain> toGameListController(List<ListUserWithGameResponseDTO> domainGameList) {
    return domainGameList.stream()
        .map(element -> {
          UserFilterDomain controllerGameList = new UserFilterDomain(element.id(), element.username(), null);
          return controllerGameList;
        })
        .toList();
  }

}
