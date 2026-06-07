package io.github.dr3amer1.backend.domain.repository;

import io.github.dr3amer1.backend.domain.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByUserId(Long userId);

    List<OrderEntity> findByUserIdOrderByCreatedAtDesc(
            Long userId
    );

}
