package com.milos.tickethub.repository;

import com.milos.tickethub.entity.Ticket;
import com.milos.tickethub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> findByUser(User user);
}
