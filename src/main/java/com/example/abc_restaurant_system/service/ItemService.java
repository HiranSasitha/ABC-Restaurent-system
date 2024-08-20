package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.ItemDto;
import com.example.abc_restaurant_system.entity.BranchItem;
import com.example.abc_restaurant_system.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    String save(ItemDto itemDto);

    String update(ItemDto itemDto,Integer itemId);

    List<Item> getAll();

    List<BranchItem> getBranchByItem(Integer itemId);
}
