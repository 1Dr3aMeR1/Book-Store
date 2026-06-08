package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.SupportMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportMessageRepository
        extends JpaRepository<SupportMessageEntity, Long> {

    List<SupportMessageEntity>
    findByTicketIdOrderByCreatedAtAsc(
            Long ticketId
    );
}