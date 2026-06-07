package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.application.exception.EntityNotFoundException;
import io.github.dr3amer1.backend.domain.model.PaymentMethodEntity;
import io.github.dr3amer1.backend.domain.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional(readOnly = true)
    public List<PaymentMethodEntity> getAllMethods() {

        return paymentMethodRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PaymentMethodEntity getById(Long id) {

        return paymentMethodRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Payment method not found: " + id
                        ));
    }
}