package io.github.dr3amer1.backend.presentation.dto.inventory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponse {

    private Long id;

    private Long storeId;

    private String storeName;

    private Long bookId;

    private String bookTitle;

    private Integer quantity;
}