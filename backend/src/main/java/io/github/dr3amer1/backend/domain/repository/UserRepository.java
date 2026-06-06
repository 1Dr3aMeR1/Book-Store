package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<UserEntity, Long> {
}