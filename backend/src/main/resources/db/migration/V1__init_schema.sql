CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,

    email VARCHAR(255) NOT NULL UNIQUE,

    password_hash VARCHAR(255) NOT NULL,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    role VARCHAR(30) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE books
(
    id BIGSERIAL PRIMARY KEY,

    title VARCHAR(255) NOT NULL,

    author VARCHAR(255) NOT NULL,

    isbn VARCHAR(20) UNIQUE,

    description TEXT,

    price NUMERIC(10,2) NOT NULL,

    preview_url TEXT,

    publication_year INTEGER,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE stores
(
    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(255) NOT NULL,

    address TEXT NOT NULL
);

CREATE TABLE inventory
(
    id BIGSERIAL PRIMARY KEY,

    store_id BIGINT NOT NULL,

    book_id BIGINT NOT NULL,

    quantity INTEGER NOT NULL,

    CONSTRAINT fk_inventory_store
        FOREIGN KEY (store_id)
            REFERENCES stores(id),

    CONSTRAINT fk_inventory_book
        FOREIGN KEY (book_id)
            REFERENCES books(id)
);