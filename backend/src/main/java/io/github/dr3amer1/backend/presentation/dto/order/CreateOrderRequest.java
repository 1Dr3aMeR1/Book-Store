package io.github.dr3amer1.backend.presentation.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    private List<CreateOrderItemRequest> items;
}