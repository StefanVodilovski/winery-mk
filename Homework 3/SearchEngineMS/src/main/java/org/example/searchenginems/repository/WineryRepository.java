package org.example.searchenginems.repository;

import org.example.searchenginems.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Queue;

@Repository
public interface WineryRepository extends JpaRepository<Winery, Long> {
    @Query("SELECT winery FROM Winery winery LEFT JOIN FETCH winery.wines WHERE winery.Id = :wineryId")
    Optional<Winery> findById(Long wineryId);
}
