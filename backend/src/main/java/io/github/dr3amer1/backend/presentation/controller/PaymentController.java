package io.github.dr3amer1.backend.presentation.controller;

import io.github.dr3amer1.backend.application.service.PaymentService;
import io.github.dr3amer1.backend.domain.model.PaymentEntity;
import io.github.dr3amer1.backend.presentation.dto.payment.PaymentRequest;
import io.github.dr3amer1.backend.presentation.dto.payment.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public PaymentResponse createPayment(
            @RequestBody PaymentRequest request
    ) {

        PaymentEntity payment =
                paymentService.createPayment(
                        request.getOrderId(),
                        request.getPaymentMethodId()
                );

        return toResponse(payment);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public PaymentResponse getPayment(
            @PathVariable Long id
    ) {

        return toResponse(
                paymentService.getPayment(id)
        );
    }

    private PaymentResponse toResponse(
            PaymentEntity payment
    ) {

        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(
                        payment.getOrder().getId()
                )
                .paymentMethod(
                        payment.getPaymentMethod()
                                .getName()
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