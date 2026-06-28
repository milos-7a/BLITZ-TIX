package com.milos.blitztix.service;

import com.milos.blitztix.dto.CancelTicketRequest;
import com.milos.blitztix.dto.TicketResponse;

import java.util.List;

public interface TicketService {
    TicketResponse purchaseTicket(Long eventId);
    List<TicketResponse> getMyTickets();
    List<TicketResponse> getAllTickets();
    TicketResponse cancelTicket(CancelTicketRequest request);
}
