package com.milos.tickethub.dto;

import jakarta.validation.constraints.NotNull;

public record CancelTicketRequest(
        @NotNull
        Long ticketId
) {
}
