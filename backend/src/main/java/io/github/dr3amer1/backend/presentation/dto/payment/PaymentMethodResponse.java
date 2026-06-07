package io.github.dr3amer1.backend.presentation.dto.payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentMethodResponse {

    private Long id;

    private String name;
}