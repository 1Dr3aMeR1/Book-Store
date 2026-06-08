package io.github.dr3amer1.backend.presentation.controller;

import io.github.dr3amer1.backend.application.service.SupportService;
import io.github.dr3amer1.backend.domain.model.*;
import io.github.dr3amer1.backend.presentation.dto.support.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/support")
@RequiredArgsConstructor
public class SupportController {

    private final SupportService supportService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public SupportTicketEntity createTicket(
            @RequestBody CreateTicketRequest request,
            Authentication authentication
    ) {

        return supportService.createTicket(
                authentication.getName(),
                request
        );
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public List<SupportTicketEntity> getMyTickets(
            Authentication authentication
    ) {

        return supportService.getMyTickets(
                authentication.getName()
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPPORT','ADMIN')")
    public List<SupportTicketEntity> getAllTickets() {

        return supportService.getAllTickets();
    }

    @PostMapping("/{ticketId}/messages")
    @PreAuthorize("isAuthenticated()")
    public SupportMessageEntity addMessage(
            @PathVariable Long ticketId,
            @RequestBody CreateMessageRequest request,
            Authentication authentication
    ) {

        return supportService.addMessage(
                ticketId,
                authentication.getName(),
                request
        );
    }

    @GetMapping("/{ticketId}/messages")
    @PreAuthorize("isAuthenticated()")
    public List<SupportMessageEntity> getMessages(
            @PathVariable Long ticketId
    ) {

        return supportService.getMessages(
                ticketId
        );
    }

    @PatchMapping("/{ticketId}/status")
    @PreAuthorize("hasAnyRole('SUPPORT','ADMIN')")
    public SupportTicketEntity updateStatus(
            @PathVariable Long ticketId,
            @RequestParam TicketStatus status
    ) {

        return supportService.updateStatus(
                ticketId,
                status
        );
    }
}