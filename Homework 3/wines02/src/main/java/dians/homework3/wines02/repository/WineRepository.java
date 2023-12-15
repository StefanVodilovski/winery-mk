package dians.homework3.wines02.repository;

import dians.homework3.wines02.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WineRepository extends JpaRepository<Wine,Long> {
    Optional<Wine> findById(Long wineId);
}
