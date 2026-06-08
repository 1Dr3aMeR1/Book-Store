package io.github.dr3amer1.backend.infrastructure.mapper;

import io.github.dr3amer1.backend.domain.model.PaymentEntity;
import io.github.dr3amer1.backend.presentation.dto.payment.PaymentResponse;

public class PaymentMapper {

    private PaymentMapper() {
    }

    public static PaymentResponse toResponse(
            PaymentEntity payment
    ) {

        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(
                        payment.getOrder().getId()
                )
                .paymentMethod(
                        payment.getPaymentMethod().getName()
                )
                .amount(
                        payment.getAmount()
                )
                .status(
                        payment.getStatus()
                )
                .build();
    }
}