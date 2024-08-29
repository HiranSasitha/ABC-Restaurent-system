package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.dto.BranchDto;
import com.example.abc_restaurant_system.dto.OrderDto;
import com.example.abc_restaurant_system.entity.OrderStatus;
import com.example.abc_restaurant_system.service.OrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/get-all-order-status")
    public ResponseEntity<?> getAllOrderStatus(){
        List<OrderStatus> orderStatuses = orderService.getAllOrderStatus();

        return new ResponseEntity<>(orderStatuses,HttpStatus.OK);
    }

    @PutMapping("/update-order-status/{id}/{status}")

    public ResponseEntity<?> updateOrderStatus(@PathVariable Integer id,@PathVariable Boolean status){
        JSONObject jsonObject = new JSONObject();

        String msg = orderService.updateOrderStatus(id,status);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/get-available-seat/{branchId}")
        public Integer getaAvailableSeat(@PathVariable Integer branchId){

        return orderService.getAvailableSeat(branchId);
        }

}
