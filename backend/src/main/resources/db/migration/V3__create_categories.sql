CREATE TABLE categories
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE book_categories
(
    book_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    PRIMARY KEY (book_id, category_id),

    CONSTRAINT fk_book_categories_book
        FOREIGN KEY (book_id)
            REFERENCES books(id),

    CONSTRAINT fk_book_categories_category
        FOREIGN KEY (category_id)
            REFERENCES categories(id)
);