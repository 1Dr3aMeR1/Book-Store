CREATE TABLE orders
(
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,

    status VARCHAR(50) NOT NULL,

    total_price NUMERIC(10,2) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_orders_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);

CREATE TABLE order_items
(
    id BIGSERIAL PRIMARY KEY,

    order_id BIGINT NOT NULL,

    book_id BIGINT NOT NULL,

    quantity INTEGER NOT NULL,

    price NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_order_items_order
        FOREIGN KEY (order_id)
            REFERENCES orders(id),

    CONSTRAINT fk_order_items_book
        FOREIGN KEY (book_id)
            REFERENCES books(id)
);

