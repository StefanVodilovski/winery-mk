package org.example.searchenginems.repository;


import org.example.searchenginems.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findById(Long eventId);

    @Query("SELECT event FROM Event event LEFT JOIN FETCH event.wineries WHERE event.Id = :eventId")
    Optional<Event> findByIdWithWineries(Long eventId);

}