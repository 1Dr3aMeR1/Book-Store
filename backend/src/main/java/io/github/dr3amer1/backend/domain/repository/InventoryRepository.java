package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository
        extends JpaRepository<InventoryEntity, Long> {
}
