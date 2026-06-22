package com.zholdigaliev.orderservice.service;

import com.zholdigaliev.common.exception.ResourceNotFoundException;
import com.zholdigaliev.orderservice.model.Cart;
import com.zholdigaliev.orderservice.repository.CartItemRepository;
import com.zholdigaliev.orderservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Cart findById(Long id) {
        return cartRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Корзина не найден"));
    }

    @Transactional
    public void clearCart(Long id) {
        Cart cart = findById(id);
        cartItemRepository.deleteAllByCartId(cart.getId());
    }
}
