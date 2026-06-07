package io.github.dr3amer1.backend.presentation.controller;

import io.github.dr3amer1.backend.application.service.PaymentMethodService;
import io.github.dr3amer1.backend.presentation.dto.payment.PaymentMethodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<PaymentMethodResponse> getAllMethods() {

        return paymentMethodService.getAllMethods()
                .stream()
                .map(method ->
                        PaymentMethodResponse.builder()
                                .id(method.getId())
                                .name(method.getName())
                                .build())
                .toList();
    }
}