CREATE TABLE orders
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    order_status VARCHAR(256) NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    order_address VARCHAR(256) NOT NULL,
    ordered_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP DEFAULT NULL
);

CREATE TABLE order_items
(
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    product_name VARCHAR(256) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    order_id BIGINT NOT NULL,

    CONSTRAINT fk_oi_order_id
        FOREIGN KEY (order_id)
        REFERENCES orders (id)
);