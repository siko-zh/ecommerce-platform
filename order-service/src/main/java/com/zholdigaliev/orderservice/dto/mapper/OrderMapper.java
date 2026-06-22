package com.zholdigaliev.orderservice.dto.mapper;

import com.zholdigaliev.orderservice.dto.OrderDto.OrderItemResponse;
import com.zholdigaliev.orderservice.dto.OrderDto.OrderResponse;
import com.zholdigaliev.orderservice.model.Order;
import com.zholdigaliev.orderservice.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    OrderResponse toOrderResponse(Order order);
}
