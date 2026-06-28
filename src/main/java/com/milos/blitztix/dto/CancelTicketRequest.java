package com.milos.blitztix.dto;

import jakarta.validation.constraints.NotNull;

public record CancelTicketRequest(
        @NotNull
        Long ticketId
) {
}
