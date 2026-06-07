package io.github.dr3amer1.backend.presentation.dto.order;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItemResponse {

    private Long bookId;

    private String bookTitle;

    private Integer quantity;

    private BigDecimal price;
}