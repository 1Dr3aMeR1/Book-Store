package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.domain.model.InventoryEntity;
import io.github.dr3amer1.backend.domain.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryEntity> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public InventoryEntity getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Inventory record not found: " + id));
    }

    public InventoryEntity createInventory(
            InventoryEntity inventory) {

        return inventoryRepository.save(inventory);
    }

    public InventoryEntity updateQuantity(
            Long inventoryId,
            Integer quantity) {

        InventoryEntity inventory =
                getInventoryById(inventoryId);

        inventory.setQuantity(quantity);

        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}