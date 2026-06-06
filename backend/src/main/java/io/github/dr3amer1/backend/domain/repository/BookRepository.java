package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository
        extends JpaRepository<BookEntity, Long> {
}
