package com.gdwho.api.infrastructure.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gdwho.api.infrastructure.persistence.dtos.user.UserPersistenceResponseDTO;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserDBEntity, Long>, JpaSpecificationExecutor<UserDBEntity> {

    Optional<UserDBEntity> findByUsername(String username);
    Optional<UserPersistenceResponseDTO> findPasswordAndIdByUsername(String username);
    @Query("SELECT u.dataResponse FROM UserDBEntity u WHERE u.id = :id")
    Optional<UserDBEntity> findResponseById(@Param("id") Long id);
}
