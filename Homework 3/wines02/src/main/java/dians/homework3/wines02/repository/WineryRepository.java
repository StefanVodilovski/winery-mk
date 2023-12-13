package dians.homework3.wines02.repository;

import dians.homework3.wines02.model.Winery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineryRepository extends JpaRepository<Winery, Long> {
}
