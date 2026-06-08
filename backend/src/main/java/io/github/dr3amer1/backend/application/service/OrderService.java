package io.github.dr3amer1.backend.application.service;

import io.github.dr3amer1.backend.domain.model.*;
import io.github.dr3amer1.backend.domain.repository.*;
import io.github.dr3amer1.backend.presentation.dto.order.CreateOrderItemRequest;
import io.github.dr3amer1.backend.presentation.dto.order.CreateOrderRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final InventoryRepository inventoryRepository;

    @Transactional
    public OrderEntity createOrder(
            String email,
            CreateOrderRequest request
    ) {

        UserEntity user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "User not found"
                                ));

        OrderEntity order =
                new OrderEntity();

        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalPrice(BigDecimal.ZERO);

        order = orderRepository.save(order);

        BigDecimal totalPrice =
                BigDecimal.ZERO;

        for (CreateOrderItemRequest itemRequest
                : request.getItems()) {

            BookEntity book =
                    bookRepository.findById(
                                    itemRequest.getBookId()
                            )
                            .orElseThrow(() ->
                                    new EntityNotFoundException(
                                            "Book not found"
                                    ));

            List<InventoryEntity> inventoryList =
                    inventoryRepository.findByBookId(
                            book.getId()
                    );

            int available =
                    inventoryList.stream()
                            .mapToInt(
                                    InventoryEntity::getQuantity
                            )
                            .sum();

            if (available <
                    itemRequest.getQuantity()) {

                throw new IllegalStateException(
                        "Not enough books in stock"
                );
            }

            int remaining =
                    itemRequest.getQuantity();

            for (InventoryEntity inventory
                    : inventoryList) {

                if (remaining == 0) {
                    break;
                }

                int current =
                        inventory.getQuantity();

                if (current >= remaining) {

                    inventory.setQuantity(
                            current - remaining
                    );

                    inventoryRepository.save(
                            inventory
                    );

                    remaining = 0;
                } else {

                    inventory.setQuantity(0);

                    inventoryRepository.save(
                            inventory
                    );

                    remaining -= current;
                }
            }

            OrderItemEntity orderItem =
                    new OrderItemEntity();

            orderItem.setOrder(order);
            orderItem.setBook(book);

            orderItem.setQuantity(
                    itemRequest.getQuantity()
            );

            orderItem.setPrice(
                    book.getPrice()
            );

            orderItemRepository.save(
                    orderItem
            );

            totalPrice =
                    totalPrice.add(
                            book.getPrice()
                                    .multiply(
                                            BigDecimal.valueOf(
                                                    itemRequest.getQuantity()
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

    @Transactional
    public List<OrderEntity> getMyOrders(
            String email
    ) {

        UserEntity user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "User not found"
                                ));

        return orderRepository
                .findByUserIdOrderByCreatedAtDesc(
                        user.getId()
                );
    }

    @Transactional
    public OrderEntity getOrderById(
            Long id
    ) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Order not found"
                        ));
    }

    @Transactional
    public List<OrderEntity> getAllOrders() {

        return orderRepository.findAll();
    }
}