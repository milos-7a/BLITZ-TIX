package com.milos.blitztix.dto;

import com.milos.blitztix.entity.TicketStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketResponse(
        Long id,
        String eventName,
        LocalDateTime purchaseDate,
        TicketStatus status,
        BigDecimal price
) {}
