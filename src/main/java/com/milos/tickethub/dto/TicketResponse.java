package com.milos.tickethub.dto;

import com.milos.tickethub.entity.TicketStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketResponse(
        Long id,
        String eventName,
        LocalDateTime purchaseDate,
        TicketStatus status,
        BigDecimal price
) {}
