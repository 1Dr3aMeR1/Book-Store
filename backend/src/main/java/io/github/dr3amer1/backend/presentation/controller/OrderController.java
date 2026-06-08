package io.github.dr3amer1.backend.presentation.controller;

import io.github.dr3amer1.backend.application.service.OrderService;
import io.github.dr3amer1.backend.domain.model.OrderEntity;
import io.github.dr3amer1.backend.presentation.dto.order.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderEntity createOrder(
            @RequestBody CreateOrderRequest request,
            Authentication authentication
    ) {

        return orderService.createOrder(
                authentication.getName(),
                request
        );
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public List<OrderEntity> getMyOrders(
            Authentication authentication
    ) {

        return orderService.getMyOrders(
                authentication.getName()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public OrderEntity getOrder(
            @PathVariable Long id
    ) {

        return orderService.getOrderById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderEntity> getAllOrders() {

        return orderService.getAllOrders();
    }
}