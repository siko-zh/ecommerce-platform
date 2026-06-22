CREATE TABLE categories
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(256)
);


CREATE TABLE products
(
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(100) NOT NULL,
    description    VARCHAR(256),
    price          DECIMAL(10, 2)   NOT NULL CHECK (price >= 0),
    stock_quantity INT          NOT NULL DEFAULT 0 CHECK (stock_quantity >= 0),
    category_id    BIGINT       NOT NULL,

    CONSTRAINT fk_oi_category_id
        FOREIGN KEY (category_id)
        REFERENCES categories (id),

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NULL
);

CREATE INDEX idx_categories_id ON categories(id);
CREATE INDEX idx_products_name ON products(name);