package com.milos.tickethub.repository;

import com.milos.tickethub.entity.Ticket;
import com.milos.tickethub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByUser(User user);
    boolean existsByEventId(Long id);
}
