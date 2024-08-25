package com.example.abc_restaurant_system.dto;

import lombok.Data;

import java.util.List;
@Data
public class OrderDto {

  private String useName;

  private String payOption;

  private Integer branchId;

  private List<OrderItemDto> orderItemDtos;
}
