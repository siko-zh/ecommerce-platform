package com.zholdigaliev.orderservice.dto.OrderDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequest {
    @NotNull(message = "Корзина отсутвует")
    private Long cartId;

    @NotBlank(message = "Адрес не может быть пустым")
    private String orderAddress;
}
