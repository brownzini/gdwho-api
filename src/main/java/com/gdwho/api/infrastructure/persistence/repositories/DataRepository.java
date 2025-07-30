package com.gdwho.api.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;

public interface DataRepository extends JpaRepository<DataDBEntity, Long> {

}
