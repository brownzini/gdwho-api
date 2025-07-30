package com.gdwho.api.infrastructure.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.gdwho.api.infrastructure.persistence.entities.DataDBEntity;

public interface DataRepository extends JpaRepository<DataDBEntity, Long> {
    
    @Query("SELECT d.value FROM DataDBEntity d WHERE d.user.id = :userId")
    List<String> findValuesByUserId(@Param("userId") Long userId);

}
