package com.zholdigaliev.orderservice.dto.OrderDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse {
    private String userId;
    private String orderStatus;
    private BigDecimal totalPrice;
    private String orderAddress;
    private List<OrderItemResponse> orderItems;
}
