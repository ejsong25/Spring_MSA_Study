package com.inflearn.orderservice.service;

import com.inflearn.orderservice.dto.OrderDto;
import com.inflearn.orderservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}