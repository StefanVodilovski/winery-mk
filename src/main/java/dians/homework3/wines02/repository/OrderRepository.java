package dians.homework3.wines02.repository;

import dians.homework3.wines02.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderWines WHERE o.Id = :orderId")
    Optional<Order> findById(Long orderId);
}

