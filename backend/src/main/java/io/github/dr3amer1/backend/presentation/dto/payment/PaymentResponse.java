package io.github.dr3amer1.backend.presentation.dto.payment;

import io.github.dr3amer1.backend.domain.model.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentResponse {

    private Long id;

    private Long orderId;

    private String paymentMethod;

    private BigDecimal amount;

    private PaymentStatus status;
}