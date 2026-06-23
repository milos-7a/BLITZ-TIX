package com.milos.tickethub.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long  ticketId) {
        super("Ticket with id " + ticketId + " not found");
    }
}
