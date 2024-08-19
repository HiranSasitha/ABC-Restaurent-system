package com.example.abc_restaurant_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String name;
    private String description;

    private String userName;

    private Boolean isActivate;
}
