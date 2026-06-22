package com.milos.tickethub.mapper;

import com.milos.tickethub.dto.TicketResponse;
import com.milos.tickethub.entity.Event;
import com.milos.tickethub.entity.Ticket;
import com.milos.tickethub.entity.TicketStatus;
import com.milos.tickethub.entity.User;

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
    public static Ticket toEntity(User user, Event event) {
        return Ticket.builder()
                .user(user)
                .event(event)
                .purchaseDate(LocalDateTime.now())
                .status(TicketStatus.PURCHASED)
                .price(event.getPrice())
                .build();
    }
}
