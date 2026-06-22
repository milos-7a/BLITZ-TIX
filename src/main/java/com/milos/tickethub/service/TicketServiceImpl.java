package com.milos.tickethub.service;

import com.milos.tickethub.dto.TicketResponse;
import com.milos.tickethub.entity.Event;
import com.milos.tickethub.entity.Ticket;
import com.milos.tickethub.entity.User;
import com.milos.tickethub.exception.EventNotFoundException;
import com.milos.tickethub.exception.UserNotFoundException;
import com.milos.tickethub.mapper.TicketMapper;
import com.milos.tickethub.repository.EventRepository;
import com.milos.tickethub.repository.TicketRepository;
import com.milos.tickethub.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public TicketResponse purchaseTicket(Long eventId){
        User user = getUserFromAuth();

        int updatedRows = eventRepository.incrementSoldTickets(eventId);

        if (updatedRows == 0){
            throw new RuntimeException("Event is sold out");
        }

        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));

        Ticket ticket = TicketMapper.toEntity(user, event);
        Ticket saved = ticketRepository.save(ticket);

        return TicketMapper.toResponse(saved);
    }
    @Override
    public List<TicketResponse> getMyTickets(){
        User user = getUserFromAuth();
        List<Ticket> tickets = ticketRepository.findByUser(user);
        return tickets.stream().map(TicketMapper::toResponse).toList();
    }
    @Override
    public List<TicketResponse> getAllTickets(){
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketMapper::toResponse).toList();
    }


    private User getUserFromAuth(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
