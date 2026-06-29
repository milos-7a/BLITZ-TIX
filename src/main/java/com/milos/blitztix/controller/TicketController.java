package com.milos.blitztix.controller;

import com.milos.blitztix.dto.CancelTicketRequest;
import com.milos.blitztix.dto.TicketResponse;
import com.milos.blitztix.service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/purchase/{eventId}")
    public TicketResponse purchaseTicket(@PathVariable Long eventId){
        return ticketService.purchaseTicket(eventId);
    }

    @GetMapping("/my")
    public List<TicketResponse> getMyTickets(){
        return ticketService.getMyTickets();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TicketResponse> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TicketResponse cancelTicket(@Valid @RequestBody CancelTicketRequest request){
        return ticketService.cancelTicket(request);
    }

}
