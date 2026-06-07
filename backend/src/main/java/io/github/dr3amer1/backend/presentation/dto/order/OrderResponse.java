package io.github.dr3amer1.backend.presentation.dto.order;

import io.github.dr3amer1.backend.domain.model.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private Long id;

    private OrderStatus status;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private List<OrderItemResponse> items;
}