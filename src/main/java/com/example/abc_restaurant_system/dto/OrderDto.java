package com.example.abc_restaurant_system.dto;

import lombok.Data;

import java.util.List;
@Data
public class OrderDto {

  private String userName;

  private String payOption;

  private Integer bookingSeat;

  private String orderOption;

  private Integer branchId;

  private List<OrderItemDto> orderItemDtos;
}
