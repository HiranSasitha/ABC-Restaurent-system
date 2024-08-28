package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.dto.CategoryDto;
import com.example.abc_restaurant_system.entity.Category;
import com.example.abc_restaurant_system.service.CategoryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
        JSONObject jsonObject = new JSONObject();

        String msg = categoryService.createCategory(categoryDto);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")

    public ResponseEntity<?> getAll(){
        List<Category> categories = categoryService.getAll();

        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        JSONObject jsonObject = new JSONObject();

        String msg = categoryService.updateCategory(categoryDto,id);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/get-all-active")

    public ResponseEntity<?> getAllActive(){
        List<Category> categories = categoryService.getAllActive();

        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
}
