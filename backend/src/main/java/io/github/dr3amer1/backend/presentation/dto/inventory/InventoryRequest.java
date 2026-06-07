package io.github.dr3amer1.backend.presentation.dto.inventory;

import lombok.Data;

@Data
public class InventoryRequest {

    private Long storeId;

    private Long bookId;

    private Integer quantity;
}
