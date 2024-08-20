package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.ItemDto;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    String save(ItemDto itemDto);

    String update(ItemDto itemDto,Integer itemId);
}
