package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.entity.Branch;
import com.example.abc_restaurant_system.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
