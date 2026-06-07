package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.application.exception.EntityNotFoundException;
import io.github.dr3amer1.backend.domain.model.BookEntity;
import io.github.dr3amer1.backend.domain.model.InventoryEntity;
import io.github.dr3amer1.backend.domain.model.StoreEntity;
import io.github.dr3amer1.backend.domain.repository.BookRepository;
import io.github.dr3amer1.backend.domain.repository.InventoryRepository;
import io.github.dr3amer1.backend.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private final StoreRepository storeRepository;

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<InventoryEntity> getBookAvailability(
            Long bookId) {

        return inventoryRepository.findByBookId(bookId);
    }

    public InventoryEntity createInventory(
            Long storeId,
            Long bookId,
            Integer quantity) {

        StoreEntity store =
                storeRepository.findById(storeId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Store not found: " + storeId));

        BookEntity book =
                bookRepository.findById(bookId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Book not found: " + bookId));

        InventoryEntity inventory =
                new InventoryEntity();

        inventory.setStore(store);
        inventory.setBook(book);
        inventory.setQuantity(quantity);

        return inventoryRepository.save(
                inventory);
    }

    public InventoryEntity updateQuantity(
            Long inventoryId,
            Integer quantity) {

        InventoryEntity inventory =
                inventoryRepository.findById(
                                inventoryId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Inventory not found: "
                                                + inventoryId));

        inventory.setQuantity(quantity);

        return inventoryRepository.save(
                inventory);
    }
}