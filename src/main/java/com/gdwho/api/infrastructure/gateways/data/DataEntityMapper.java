package com.gdwho.api.infrastructure.gateways.data;

import java.util.List;

import com.gdwho.api.domain.entities.data.DataDomainEntity;
import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

public class DataEntityMapper {

  List<DataDBEntity> toEntityList(List<String> inputsList, UserDBEntity userDBEntity) {
    return inputsList.stream()
        .map(elementData -> {
          DataDBEntity data = new DataDBEntity(elementData);
          data.setUser(userDBEntity);
          return data;
        })
        .toList();
  }

  List<DataDomainEntity> toDomainList(List<DataDBEntity> inputList, UserDBEntity user) {
    return inputList.stream()
        .map(data -> new DataDomainEntity(data.getId(), data.getValue()))
        .toList();
  }

}
