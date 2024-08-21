package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.dto.CategoryDto;
import com.example.abc_restaurant_system.dto.ItemDto;
import com.example.abc_restaurant_system.entity.Branch;
import com.example.abc_restaurant_system.entity.BranchItem;
import com.example.abc_restaurant_system.entity.Item;
import com.example.abc_restaurant_system.service.ItemService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveCategory(@RequestBody ItemDto itemDto){
        JSONObject jsonObject = new JSONObject();

        String msg = itemService.save(itemDto);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){

        List<Item> items = itemService.getAll();

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/get-all-branch-by-item/{id}")
    public ResponseEntity<?> getAllBranchByItem(@PathVariable Integer id){

        List<BranchItem> items = itemService.getBranchByItem(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/get-all-item-by-branch/{id}")
    public ResponseEntity<?> getAllItemByBranch(@PathVariable Integer id){

        List<BranchItem> items = itemService.getItemByBranch(id);

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@RequestBody ItemDto itemDto,@PathVariable Integer id){
        JSONObject jsonObject = new JSONObject();

        String msg = itemService.update(itemDto,id);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }


}
