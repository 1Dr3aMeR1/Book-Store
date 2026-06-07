package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.application.exception.EntityNotFoundException;
import io.github.dr3amer1.backend.domain.model.*;
import io.github.dr3amer1.backend.domain.repository.OrderRepository;
import io.github.dr3amer1.backend.domain.repository.PaymentMethodRepository;
import io.github.dr3amer1.backend.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final OrderRepository orderRepository;

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentEntity createPayment(
            Long orderId,
            Long paymentMethodId
    ) {

        OrderEntity order =
                orderRepository.findById(orderId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Order not found: " + orderId
                                ));

        PaymentMethodEntity paymentMethod =
                paymentMethodRepository.findById(
                                paymentMethodId
                        )
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Payment method not found: "
                                                + paymentMethodId
                                ));

        PaymentEntity payment =
                new PaymentEntity();

        payment.setOrder(order);

        payment.setPaymentMethod(
                paymentMethod
        );

        payment.setAmount(
                order.getTotalPrice()
        );

        payment.setStatus(
                PaymentStatus.COMPLETED
        );

        payment.setCreatedAt(
                LocalDateTime.now()
        );

        order.setStatus(
                OrderStatus.PAID
        );

        orderRepository.save(order);

        return paymentRepository.save(
                payment
        );
    }

    @Transactional(readOnly = true)
    public PaymentEntity getPayment(
            Long id
    ) {

        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Payment not found: " + id
                        ));
    }
}