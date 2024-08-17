package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.service.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DataInitializer dataInitializer;

    @PostConstruct
    public void setDataInitialize(){
        dataInitializer.dataInitials();
    }
}
