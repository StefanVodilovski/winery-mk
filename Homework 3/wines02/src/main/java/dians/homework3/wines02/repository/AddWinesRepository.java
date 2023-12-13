package dians.homework3.wines02.repository;

import dians.homework3.wines02.model.AddWines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddWinesRepository extends JpaRepository<AddWines, Long> {
}
