package io.github.dr3amer1.backend.infrastructure.mapper;

import io.github.dr3amer1.backend.domain.model.InventoryEntity;
import io.github.dr3amer1.backend.presentation.dto.inventory.InventoryResponse;

public class InventoryMapper {

    private InventoryMapper() {
    }

    public static InventoryResponse toResponse(
            InventoryEntity inventory
    ) {

        return InventoryResponse.builder()
                .id(inventory.getId())
                .storeId(
                        inventory.getStore().getId()
                )
                .storeName(
                        inventory.getStore().getName()
                )
                .bookId(
                        inventory.getBook().getId()
                )
                .bookTitle(
                        inventory.getBook().getTitle()
                )
                .quantity(
                        inventory.getQuantity()
                )
                .build();
    }
}