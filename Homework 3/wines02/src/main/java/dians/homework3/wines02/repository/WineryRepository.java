package dians.homework3.wines02.repository;

import dians.homework3.wines02.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WineryRepository extends JpaRepository<Winery, Long> {
    Optional<Winery> findById(Long wineryId);
}
