package io.github.dr3amer1.backend.presentation.dto.payment;

import lombok.Data;

@Data
public class PaymentRequest {

    private Long orderId;

    private Long paymentMethodId;
}