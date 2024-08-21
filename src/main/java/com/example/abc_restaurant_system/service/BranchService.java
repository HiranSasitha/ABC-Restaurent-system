package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.dto.BranchDto;
import com.example.abc_restaurant_system.entity.Branch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BranchService {

    List<Branch> getAll();

    String save(BranchDto branchDto);

    String update(BranchDto branchDto,Integer id);

}
