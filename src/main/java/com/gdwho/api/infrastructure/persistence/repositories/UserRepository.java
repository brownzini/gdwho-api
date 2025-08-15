package com.gdwho.api.infrastructure.persistence.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gdwho.api.infrastructure.persistence.dtos.user.ListUserWithGameResponseDTO;
import com.gdwho.api.infrastructure.persistence.dtos.user.UserPersistenceResponseDTO;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;

import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserDBEntity, Long>, JpaSpecificationExecutor<UserDBEntity> {

    Optional<UserDBEntity> findByUsername(String username);

    Optional<UserPersistenceResponseDTO> findPasswordAndIdByUsername(String username);

    @Query("SELECT u FROM UserDBEntity u WHERE u.id = :id AND u.dataResponse IS NULL")
    Optional<UserDBEntity> findIfResponseIsNull(@Param("id") Long id);

    @Query("SELECT u FROM UserDBEntity u WHERE u.id = :id AND u.dataResponse IS NOT NULL")
    Optional<UserDBEntity> findIfResponseIsNotNull(@Param("id") Long id);

    @Query("SELECT u.dataResponse FROM UserDBEntity u WHERE u.id = :id")
    Optional<String> findResponseById(@Param("id") Long id);

    @Query("SELECT new com.gdwho.api.infrastructure.persistence.dtos.user.ListUserWithGameResponseDTO(u.id, u.username) "
            +
            "FROM UserDBEntity u WHERE u.dataResponse IS NOT NULL")
    List<ListUserWithGameResponseDTO> findGamesWithDataResponseIfNotNull();
}
