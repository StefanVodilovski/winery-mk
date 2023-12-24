package dians.homework3.wines02.repository;

import dians.homework3.wines02.model.Cart;
import dians.homework3.wines02.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
