package io.github.dr3amer1.backend.presentation.dto.order;

import lombok.Data;

@Data
public class CreateOrderItemRequest {

    private Long bookId;

    private Integer quantity;
}