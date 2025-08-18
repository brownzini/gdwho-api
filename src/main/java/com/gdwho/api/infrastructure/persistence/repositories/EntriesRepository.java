package com.gdwho.api.infrastructure.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import com.gdwho.api.infrastructure.persistence.entities.EntriesDBEntity;

public interface EntriesRepository extends JpaRepository<EntriesDBEntity, Long> {

    boolean existsByIdAndUser_Id(Long entrieId, Long userId);

    @Query("SELECT d FROM EntriesDBEntity d WHERE d.user.id = :userId")
    List<EntriesDBEntity> findAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("""
                UPDATE EntriesDBEntity d
                SET
                    d.input = COALESCE(:input, d.input),
                    d.output = COALESCE(:output, d.output),
                    d.label = COALESCE(:label, d.label)
                WHERE
                    d.id = :id
                    AND (:input IS NULL OR d.input <> :input)
                    AND (:output IS NULL OR d.output <> :output)
                    AND (:label IS NULL OR d.label <> :label)
            """)
    void updateEntrieById(@Param("id") Long id,
            @Param("input") String input,
            @Param("output") String output,
            @Param("label") Double label);
        
    @Modifying
    @Query("DELETE FROM EntriesDBEntity e WHERE e.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
