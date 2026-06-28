package com.milos.blitztix.service;

import com.milos.blitztix.dto.CancelTicketRequest;
import com.milos.blitztix.dto.TicketResponse;
import com.milos.blitztix.entity.Event;
import com.milos.blitztix.entity.Ticket;
import com.milos.blitztix.entity.TicketStatus;
import com.milos.blitztix.entity.User;
import com.milos.blitztix.exception.EventNotFoundException;
import com.milos.blitztix.exception.TicketNotFoundException;
import com.milos.blitztix.exception.UserNotFoundException;
import com.milos.blitztix.mapper.TicketMapper;
import com.milos.blitztix.repository.EventRepository;
import com.milos.blitztix.repository.TicketRepository;
import com.milos.blitztix.repository.UserRepository;
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

        Ticket ticket = TicketMapper.toEntity(user, event, TicketStatus.PURCHASED);
        Ticket saved = ticketRepository.save(ticket);

        return TicketMapper.toResponse(saved);
    }
    @Override
    @Transactional
    public TicketResponse cancelTicket(CancelTicketRequest request){
        Ticket ticket = ticketRepository.findById(request.ticketId()).orElseThrow(() -> new TicketNotFoundException(request.ticketId()));
        if (ticket.getStatus() == TicketStatus.CANCELLED) {
            throw new IllegalStateException("Ticket is already cancelled");
        }
        ticket.setStatus(TicketStatus.CANCELLED);
        Event event = ticket.getEvent();
        eventRepository.decrementSoldTickets(event.getId());
        return TicketMapper.toResponse(ticket);
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
