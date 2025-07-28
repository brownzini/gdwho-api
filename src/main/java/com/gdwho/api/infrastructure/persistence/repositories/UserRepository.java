package com.gdwho.api.infrastructure.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.gdwho.api.infrastructure.persistence.dtos.user.UserResponseDTO;
import com.gdwho.api.infrastructure.persistence.entities.UserDBEntity;


public interface UserRepository extends JpaRepository<UserDBEntity, Long>, JpaSpecificationExecutor<UserDBEntity> {
    Optional<UserDBEntity> findByUsername(String username);
    Optional<UserResponseDTO> findDTOResponseByUsername(String username);
}
