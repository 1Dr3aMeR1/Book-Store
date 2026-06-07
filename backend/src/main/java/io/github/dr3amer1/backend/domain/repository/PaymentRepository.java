package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository
        extends JpaRepository<PaymentEntity, Long> {
}
