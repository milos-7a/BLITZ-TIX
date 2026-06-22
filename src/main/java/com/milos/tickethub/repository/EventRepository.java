package com.milos.tickethub.repository;

import com.milos.tickethub.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Modifying
    @Query("""
    UPDATE Event e
    SET e.soldTickets = e.soldTickets + 1
    WHERE e.id = :eventId
    AND e.soldTickets < e.capacity
""")
    int incrementSoldTickets(@Param("eventId") Long eventId);
}
