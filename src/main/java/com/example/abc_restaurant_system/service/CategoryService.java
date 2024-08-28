package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.CategoryDto;
import com.example.abc_restaurant_system.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    String createCategory(CategoryDto categoryDto);

    List<Category> getAll();

    String updateCategory(CategoryDto categoryDto,Integer id);

    List<Category> getAllActive();
}
