package io.github.dr3amer1.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "support_messages")
@Getter
@Setter
public class SupportMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private SupportTicketEntity ticket;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
