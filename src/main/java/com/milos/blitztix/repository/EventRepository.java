package com.milos.blitztix.repository;

import com.milos.blitztix.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

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

    @Modifying
    @Query("UPDATE Event e SET e.soldTickets = e.soldTickets - 1 WHERE e.id = :eventId AND e.soldTickets > 0")
    void decrementSoldTickets(@Param("eventId") Long eventId);


    @Query("SELECT e FROM Event e WHERE " +
            "(cast(:title as string) IS NULL OR LOWER(e.title) LIKE LOWER(cast(:title as string))) AND " +
            "(cast(:location as string) IS NULL OR e.location = :location) AND " +
            "(cast(:date_time as timestamp) IS NULL OR e.dateTime = :date_time)")
    Page<Event> findWithFilters(
            @Param("title") String title,
            @Param("location") String location,
            @Param("date_time") LocalDateTime dateTime,
            Pageable pageable);
}
