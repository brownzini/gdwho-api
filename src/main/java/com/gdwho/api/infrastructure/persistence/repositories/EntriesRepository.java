package com.gdwho.api.infrastructure.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.gdwho.api.infrastructure.persistence.entities.EntriesDBEntity;

public interface EntriesRepository extends JpaRepository<EntriesDBEntity, Long> {

    @Query("SELECT d FROM EntriesDBEntity d WHERE d.user.id = :userId")
    List<EntriesDBEntity> findAllByUserId(@Param("userId") Long userId);

}
