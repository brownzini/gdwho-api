package com.gdwho.api.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdwho.api.infrastructure.persistence.entities.GuessDBEntity;

public interface GuessRepository extends JpaRepository<GuessDBEntity, Long> {

}
