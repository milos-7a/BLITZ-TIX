package com.milos.blitztix.mapper;

import com.milos.blitztix.dto.TicketResponse;
import com.milos.blitztix.entity.Event;
import com.milos.blitztix.entity.Ticket;
import com.milos.blitztix.entity.TicketStatus;
import com.milos.blitztix.entity.User;

import java.time.LocalDateTime;

public class TicketMapper {
    public static TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getEvent().getTitle(),
                ticket.getPurchaseDate(),
                ticket.getStatus(),
                ticket.getPrice()
        );
    }
    public static Ticket toEntity(User user, Event event, TicketStatus ticketStatus) {
        return Ticket.builder()
                .user(user)
                .event(event)
                .purchaseDate(LocalDateTime.now())
                .status(ticketStatus)
                .price(event.getPrice())
                .build();
    }
}
