package com.example.stickers.controller;

import com.example.stickers.dto.OrderRequestDto;
import com.example.stickers.dto.OrderResponseDto;
import com.example.stickers.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    private final IOrderService iOrderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        iOrderService.createOrder(orderRequestDto);
        return ResponseEntity.ok("Order created successfully");
    }


    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> loadCustomerOrders(){
        return ResponseEntity.ok(iOrderService.getCustomerOrders());
    }
}
