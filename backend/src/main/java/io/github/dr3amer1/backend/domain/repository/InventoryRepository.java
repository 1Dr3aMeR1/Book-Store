package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository
        extends JpaRepository<InventoryEntity, Long> {

    List<InventoryEntity> findByBookId(Long bookId);

    List<InventoryEntity> findAllByBookId(Long bookId);

    Optional<InventoryEntity> findByStoreIdAndBookId(
            Long storeId,
            Long bookId
    );
}
