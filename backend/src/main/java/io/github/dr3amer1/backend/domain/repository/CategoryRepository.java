package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<CategoryEntity, Long> {
}
