package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.SupportTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportTicketRepository
        extends JpaRepository<SupportTicketEntity, Long> {

    List<SupportTicketEntity>
    findByUserIdOrderByCreatedAtDesc(
            Long userId
    );
}