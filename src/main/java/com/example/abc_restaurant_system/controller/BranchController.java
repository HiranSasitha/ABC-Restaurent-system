package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.dto.BranchDto;
import com.example.abc_restaurant_system.dto.ItemDto;
import com.example.abc_restaurant_system.entity.Branch;
import com.example.abc_restaurant_system.service.BranchService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;


    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){

        List<Branch> branches = branchService.getAll();

        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveCategory(@RequestBody BranchDto branchDto){
        JSONObject jsonObject = new JSONObject();

        String msg = branchService.save(branchDto);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@RequestBody BranchDto branchDto,@PathVariable Integer id){
        JSONObject jsonObject = new JSONObject();

        String msg = branchService.update(branchDto,id);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }

}
