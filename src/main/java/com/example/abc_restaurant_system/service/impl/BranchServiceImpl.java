package com.example.abc_restaurant_system.service.impl;

import com.example.abc_restaurant_system.dto.BranchDto;
import com.example.abc_restaurant_system.entity.*;
import com.example.abc_restaurant_system.repository.*;
import com.example.abc_restaurant_system.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ItemBranchRepo itemBranchRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BranchRepository branchRepository;


    @Override
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }

    @Override
    @Transactional
    public String save(BranchDto branchDto) {
        User user = userRepository.findByUserName(branchDto.getCreatedUser());

        if (user != null) {

              Branch branch = new Branch();
              branch.setIsActive(branchDto.getIsActive());
              branch.setCreatedUser(user);
              branch.setCreatedDate(new Date());
              branch.setName(branchDto.getName());
              branch.setLocation(branchDto.getLocation());
              branchRepository.save(branch);

                for (Integer id:branchDto.getItemId()){

                    Item item = itemRepo.findById(id).get();

                    if(item!= null){

                        BranchItem branchItem = new BranchItem();
                        branchItem.setItem(item);
                        branchItem.setBranch(branch);
                        branchItem.setIsActive(item.getIsActive());

                        itemBranchRepo.save(branchItem);
                    }
                }

                return "Success created";

        } else {

            return "user is invalid";
        }

    }

    @Override
    @Transactional
    public String update(BranchDto branchDto,Integer branchId) {
        User user = userRepository.findByUserName(branchDto.getCreatedUser());

        if (user != null) {

                Branch branch = branchRepository.findById(branchId).get();

            branch.setIsActive(branchDto.getIsActive());
            branch.setCreatedUser(user);
            branch.setCreatedDate(new Date());
            branch.setName(branchDto.getName());
            branch.setLocation(branchDto.getLocation());
            branchRepository.save(branch);

                itemBranchRepo.deleteAllByBranch_Id(branchId);

            for (Integer id:branchDto.getItemId()){

                Item item = itemRepo.findById(id).get();

                if(item!= null){

                    BranchItem branchItem = new BranchItem();
                    branchItem.setItem(item);
                    branchItem.setBranch(branch);
                    branchItem.setIsActive(item.getIsActive());

                    itemBranchRepo.save(branchItem);
                }
            }

                return "Success updated";

        } else {

            return "user is invalid";
        }
    }
}
