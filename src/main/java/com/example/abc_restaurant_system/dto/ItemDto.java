package com.example.abc_restaurant_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemDto {

    private String name;

    private String description;

    private Double originalPrice;

    private Double sellingPrice;

    private String createdUser;

    private Integer categoryId;

    private Boolean isActive;

    private List<Integer> branchId;
}
