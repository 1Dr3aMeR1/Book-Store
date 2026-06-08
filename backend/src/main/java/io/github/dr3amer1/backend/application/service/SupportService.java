package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.domain.model.*;
import io.github.dr3amer1.backend.domain.repository.*;
import io.github.dr3amer1.backend.presentation.dto.support.CreateMessageRequest;
import io.github.dr3amer1.backend.presentation.dto.support.CreateTicketRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportService {

    private final SupportTicketRepository supportTicketRepository;

    private final SupportMessageRepository supportMessageRepository;

    private final UserRepository userRepository;

    @Transactional
    public SupportTicketEntity createTicket(
            String email,
            CreateTicketRequest request
    ) {

        UserEntity user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "User not found"
                                ));

        SupportTicketEntity ticket =
                new SupportTicketEntity();

        ticket.setUser(user);
        ticket.setSubject(request.getSubject());

        ticket.setStatus(
                TicketStatus.OPEN
        );

        ticket.setCreatedAt(
                LocalDateTime.now()
        );

        return supportTicketRepository.save(
                ticket
        );
    }

    @Transactional
    public List<SupportTicketEntity> getMyTickets(
            String email
    ) {

        UserEntity user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "User not found"
                                ));

        return supportTicketRepository
                .findByUserIdOrderByCreatedAtDesc(
                        user.getId()
                );
    }

    @Transactional
    public List<SupportTicketEntity> getAllTickets() {

        return supportTicketRepository.findAll();
    }

    @Transactional
    public SupportMessageEntity addMessage(
            Long ticketId,
            String email,
            CreateMessageRequest request
    ) {

        SupportTicketEntity ticket =
                supportTicketRepository.findById(ticketId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Ticket not found"
                                ));

        UserEntity sender =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "User not found"
                                ));

        SupportMessageEntity message =
                new SupportMessageEntity();

        message.setTicket(ticket);

        message.setSender(sender);

        message.setMessage(
                request.getMessage()
        );

        message.setCreatedAt(
                LocalDateTime.now()
        );

        return supportMessageRepository.save(
                message
        );
    }

    @Transactional
    public List<SupportMessageEntity> getMessages(
            Long ticketId
    ) {

        return supportMessageRepository
                .findByTicketIdOrderByCreatedAtAsc(
                        ticketId
                );
    }

    @Transactional
    public SupportTicketEntity updateStatus(
            Long ticketId,
            TicketStatus status
    ) {

        SupportTicketEntity ticket =
                supportTicketRepository.findById(ticketId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Ticket not found"
                                ));

        ticket.setStatus(status);

        return supportTicketRepository.save(
                ticket
        );
    }
}