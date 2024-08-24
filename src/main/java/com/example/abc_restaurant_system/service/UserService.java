package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.CreateUserDto;
import com.example.abc_restaurant_system.entity.User;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    String saveCustomer(CreateUserDto user);

    List<User> findAll();
}
