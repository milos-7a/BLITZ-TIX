package com.milos.blitztix.repository;

import com.milos.blitztix.entity.Ticket;
import com.milos.blitztix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByUser(User user);
    boolean existsByEventId(Long id);
}
