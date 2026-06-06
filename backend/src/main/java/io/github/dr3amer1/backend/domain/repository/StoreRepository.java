package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository
        extends JpaRepository<StoreEntity, Long> {
}