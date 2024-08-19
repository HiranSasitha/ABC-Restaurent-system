package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.dto.LoginDto;
import com.example.abc_restaurant_system.dto.UserResponseDto;
import com.example.abc_restaurant_system.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("authenticate")
    public UserResponseDto createTokenAndLogin(@RequestBody LoginDto dto) throws Exception {
        return jwtService.createJwtToken(dto);
    }
}
