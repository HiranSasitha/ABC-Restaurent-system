package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    String createCategory(CategoryDto categoryDto);
}
