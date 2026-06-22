package com.zholdigaliev.orderservice.service;

import com.zholdigaliev.orderservice.dto.OrderDto.CreateOrderRequest;
import com.zholdigaliev.orderservice.dto.OrderDto.OrderResponse;
import com.zholdigaliev.orderservice.dto.ProductDto.ProductResponse;
import com.zholdigaliev.orderservice.dto.mapper.OrderMapper;
import com.zholdigaliev.orderservice.model.*;
import com.zholdigaliev.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final CartService cartService;
    private final OrderMapper mapper;

    public OrderResponse create(CreateOrderRequest request) {
        Cart cart = cartService.findById(request.getCartId());

        List<Long> productIds = cart.getCartItems().stream()
                .map(CartItem::getProductId)
                .toList();

        List<ProductResponse> productResponses = productServiceClient.getProducts(productIds);

        Map<Long, ProductResponse> productResponseMap = productResponses.stream()
                .collect(Collectors.toMap(ProductResponse::getId, productResponse -> productResponse));




        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(item -> OrderItem.builder()
                    .productId(item.getProductId())
                    .productName(productResponseMap.get(item.getProductId()).getName())
                    .quantity(item.getQuantity())
                    .price(productResponseMap.get(item.getProductId()).getPrice())
                    .build()
                )
                .toList();

        BigDecimal totalPrice = orderItems.stream()
                    .map(orderItem -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = Order.builder()
                .userId(cart.getUserId())
                .orderAddress(request.getOrderAddress())
                .orderStatus(Status.NEW)
                .orderItems(orderItems)
                .totalPrice(totalPrice)
                .build();

        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }

        Order saved = orderRepository.save(order);

        cartService.clearCart(cart.getId());

        return mapper.toOrderResponse(saved);
    }
}
