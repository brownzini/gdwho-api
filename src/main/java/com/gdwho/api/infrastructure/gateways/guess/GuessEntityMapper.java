package com.gdwho.api.infrastructure.gateways.guess;

import java.util.List;

import com.gdwho.api.domain.entities.guess.GuessDomainEntity;
import com.gdwho.api.infrastructure.persistence.entities.GuessDBEntity;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

public class GuessEntityMapper {

  List<GuessDBEntity> toEntityList(List<String> inputsList, UserDBEntity userDBEntity) {
    return inputsList.stream()
        .map(input -> {
          GuessDBEntity guess = new GuessDBEntity(input);
          guess.setUser(userDBEntity);
          return guess;
        })
        .toList();
  }

  List<GuessDomainEntity> toDomainList(List<GuessDBEntity> inputList, UserDBEntity user) {
    return inputList.stream()
        .map(guess -> new GuessDomainEntity(guess.getId(), guess.getInput()))
        .toList();
  }

}
