package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    String save(OrderDto orderDto);
}
