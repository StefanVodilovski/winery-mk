package org.example.searchenginems.repository;

import org.example.searchenginems.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WineRepository extends JpaRepository<Wine,Long> {
    @Query("SELECT wine FROM Wine wine LEFT JOIN FETCH wine.winery WHERE wine.Id = :wineId")
    Optional<Wine> findById(Long wineId);
}
