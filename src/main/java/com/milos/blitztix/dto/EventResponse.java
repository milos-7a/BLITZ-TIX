package com.milos.blitztix.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventResponse(
        Long id,
        String title,
        String description,
        String location,
        LocalDateTime dateTime,
        BigDecimal price,
        Integer availableTickets
) {
}
