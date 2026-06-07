package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository
        extends JpaRepository<PaymentMethodEntity, Long> {
}