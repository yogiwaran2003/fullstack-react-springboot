package com.example.stickers.service;

import com.example.stickers.dto.OrderRequestDto;
import com.example.stickers.dto.OrderResponseDto;
import com.example.stickers.entity.Order;

import java.util.List;

public interface IOrderService {

    void createOrder(OrderRequestDto orderRequest);

    List<OrderResponseDto> getCustomerOrders();

    List<OrderResponseDto> getAllPendingOrders();

    Order updateOrderStatus(Long orderId, String orderStatus);
}
