package io.github.dr3amer1.backend.presentation.controller;

import io.github.dr3amer1.backend.application.service.InventoryService;
import io.github.dr3amer1.backend.domain.model.InventoryEntity;
import io.github.dr3amer1.backend.presentation.dto.inventory.InventoryRequest;
import io.github.dr3amer1.backend.presentation.dto.inventory.InventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/book/{bookId}")
    @PreAuthorize("isAuthenticated()")
    public List<InventoryResponse> getAvailability(
            @PathVariable Long bookId) {

        return inventoryService
                .getBookAvailability(bookId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponse createInventory(
            @RequestBody InventoryRequest request) {

        InventoryEntity inventory =
                inventoryService.createInventory(
                        request.getStoreId(),
                        request.getBookId(),
                        request.getQuantity()
                );

        return toResponse(inventory);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponse updateQuantity(
            @PathVariable Long id,
            @RequestBody InventoryRequest request) {

        InventoryEntity inventory =
                inventoryService.updateQuantity(
                        id,
                        request.getQuantity()
                );

        return toResponse(inventory);
    }

    private InventoryResponse toResponse(
            InventoryEntity inventory) {

        return InventoryResponse.builder()
                .id(inventory.getId())
                .storeId(
                        inventory.getStore().getId())
                .storeName(
                        inventory.getStore().getName())
                .bookId(
                        inventory.getBook().getId())
                .bookTitle(
                        inventory.getBook().getTitle())
                .quantity(
                        inventory.getQuantity())
                .build();
    }
}