package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.dto.CategoryDto;
import com.example.abc_restaurant_system.service.CategoryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
        JSONObject jsonObject = new JSONObject();

        String msg = categoryService.createCategory(categoryDto);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }
}
