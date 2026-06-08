package io.github.dr3amer1.backend.infrastructure.mapper;

import io.github.dr3amer1.backend.domain.model.PaymentMethodEntity;
import io.github.dr3amer1.backend.presentation.dto.payment.PaymentMethodResponse;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMapper {

    private PaymentMethodMapper() {
    }

    public PaymentMethodResponse toResponse(
            PaymentMethodEntity method
    ) {

        return PaymentMethodResponse.builder()
                .id(method.getId())
                .name(method.getName())
                .build();
    }
}