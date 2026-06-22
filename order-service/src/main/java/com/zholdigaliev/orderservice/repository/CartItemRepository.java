package com.zholdigaliev.orderservice.repository;

import com.zholdigaliev.orderservice.model.Cart;
import com.zholdigaliev.orderservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
