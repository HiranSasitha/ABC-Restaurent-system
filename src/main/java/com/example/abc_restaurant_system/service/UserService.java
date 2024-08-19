package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.CreateUserDto;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String saveCustomer(CreateUserDto user);
}
