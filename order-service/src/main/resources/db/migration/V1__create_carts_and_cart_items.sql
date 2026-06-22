CREATE TABLE carts
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL
);

CREATE TABLE cart_items
(
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    cart_id BIGINT NOT NULL,

    CONSTRAINT fk_oi_cart_id
        FOREIGN KEY (cart_id)
        REFERENCES carts (id)
);