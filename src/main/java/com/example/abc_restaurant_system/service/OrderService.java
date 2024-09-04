package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.OrderDto;
import com.example.abc_restaurant_system.entity.Order;
import com.example.abc_restaurant_system.entity.OrderStatus;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface OrderService {
    String save(OrderDto orderDto) throws JRException, IOException;

    List<OrderStatus> getAllOrderStatus();

    String updateOrderStatus(Integer id, Boolean status);

    Integer getAvailableSeat(Integer branchId);

    List<OrderStatus> getOrdersByUser(String userName);

    void exportReport(Order order) throws IOException, JRException;
}
