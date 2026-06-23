package com.milos.tickethub.service;

import com.milos.tickethub.dto.CancelTicketRequest;
import com.milos.tickethub.dto.TicketResponse;

import java.util.List;

public interface TicketService {
    TicketResponse purchaseTicket(Long eventId);
    List<TicketResponse> getMyTickets();
    List<TicketResponse> getAllTickets();
    TicketResponse cancelTicket(CancelTicketRequest request);
}
