package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.dto.BranchDto;
import com.example.abc_restaurant_system.dto.OrderDto;
import com.example.abc_restaurant_system.service.OrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")

    public ResponseEntity<?> save(@RequestBody OrderDto orderDto){
        JSONObject jsonObject = new JSONObject();

        String msg = orderService.save(orderDto);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }
}
