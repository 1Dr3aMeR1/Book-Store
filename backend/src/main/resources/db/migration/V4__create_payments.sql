CREATE TABLE payment_methods
(
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE payments
(
    id BIGSERIAL PRIMARY KEY,

    order_id BIGINT NOT NULL,

    payment_method_id BIGINT NOT NULL,

    amount NUMERIC(10,2) NOT NULL,

    status VARCHAR(50) NOT NULL,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_payment_order
        FOREIGN KEY (order_id)
            REFERENCES orders(id),

    CONSTRAINT fk_payment_method
        FOREIGN KEY (payment_method_id)
            REFERENCES payment_methods(id)
);