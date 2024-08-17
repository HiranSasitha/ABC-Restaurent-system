package com.example.abc_restaurant_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping("/get")
    public ResponseEntity<?> testMsg(){
        String msg = "hiran";

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
