package com.example.abc_restaurant_system.service.impl;

import com.example.abc_restaurant_system.entity.Branch;
import com.example.abc_restaurant_system.repository.BranchRepository;
import com.example.abc_restaurant_system.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;


    @Override
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }
}
