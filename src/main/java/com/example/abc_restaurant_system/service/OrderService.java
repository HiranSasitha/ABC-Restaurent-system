package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.OrderDto;
import com.example.abc_restaurant_system.entity.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    String save(OrderDto orderDto);

    List<OrderStatus> getAllOrderStatus();

    String updateOrderStatus(Integer id, Boolean status);
}
