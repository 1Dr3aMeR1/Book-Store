package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.application.exception.EntityNotFoundException;
import io.github.dr3amer1.backend.domain.model.*;
import io.github.dr3amer1.backend.domain.repository.*;
import io.github.dr3amer1.backend.presentation.dto.order.CreateOrderItemRequest;
import io.github.dr3amer1.backend.presentation.dto.order.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final InventoryRepository inventoryRepository;

    public OrderEntity createOrder(
            String email,
            CreateOrderRequest request
    ) {

        UserEntity user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        email
                                ));

        OrderEntity order = new OrderEntity();

        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalPrice(BigDecimal.ZERO);

        order = orderRepository.save(order);

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CreateOrderItemRequest itemRequest :
                request.getItems()) {

            BookEntity book =
                    bookRepository.findById(
                                    itemRequest.getBookId())
                            .orElseThrow(() ->
                                    new EntityNotFoundException(
                                            "Book not found: "
                                                    + itemRequest.getBookId()));

            int requiredQuantity =
                    itemRequest.getQuantity();

            List<InventoryEntity> inventoryList =
                    inventoryRepository.findByBookId(
                            book.getId()
                    );

            int availableQuantity =
                    inventoryList.stream()
                            .mapToInt(
                                    InventoryEntity::getQuantity
                            )
                            .sum();

            if (availableQuantity < requiredQuantity) {

                throw new IllegalArgumentException(
                        "Not enough books in stock: "
                                + book.getTitle()
                );
            }

            int remaining =
                    requiredQuantity;

            for (InventoryEntity inventory :
                    inventoryList) {

                if (remaining <= 0) {
                    break;
                }

                int current =
                        inventory.getQuantity();

                int toTake =
                        Math.min(
                                current,
                                remaining
                        );

                inventory.setQuantity(
                        current - toTake
                );

                inventoryRepository.save(
                        inventory
                );

                remaining -= toTake;
            }

            OrderItemEntity item =
                    new OrderItemEntity();

            item.setOrder(order);
            item.setBook(book);

            item.setQuantity(
                    requiredQuantity
            );

            item.setPrice(
                    book.getPrice()
            );

            orderItemRepository.save(item);

            totalPrice =
                    totalPrice.add(
                            book.getPrice()
                                    .multiply(
                                            BigDecimal.valueOf(
                                                    requiredQuantity
                                            )
                                    )
                    );
        }

        order.setTotalPrice(
                totalPrice
        );

        return orderRepository.save(
                order
        );
    }

    @Transactional(readOnly = true)
    public List<OrderEntity> getMyOrders(
            String email
    ) {

        UserEntity user =
                userRepository.findByEmail(
                                email
                        )
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        email
                                ));

        return orderRepository
                .findByUserIdOrderByCreatedAtDesc(
                        user.getId()
                );
    }
}