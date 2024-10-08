package com.example.abc_restaurant_system.service.impl;

import com.example.abc_restaurant_system.dto.ItemDto;
import com.example.abc_restaurant_system.entity.*;
import com.example.abc_restaurant_system.repository.*;
import com.example.abc_restaurant_system.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

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
    @Transactional
    public String save(ItemDto itemDto) {
        User user = userRepository.findByUserName(itemDto.getCreatedUser());

        if (user != null) {

            Category category = categoryRepository.findById(itemDto.getCategoryId()).get();

            if (category != null) {


                Item item = new Item();

                item.setName(itemDto.getName());
                item.setDescription(itemDto.getDescription());
                item.setCreatedDate(new Date());
                item.setIsActive(itemDto.getIsActive());
                item.setOriginalPrice(itemDto.getOriginalPrice());
                item.setSellingPrice(itemDto.getSellingPrice());
                item.setCreatedUser(user);
                item.setCategory(category);
                itemRepo.save(item);

                for (Integer id:itemDto.getBranchId()){

                    Branch branch = branchRepository.findById(id).get();

                    if(branch!= null){

                        BranchItem branchItem = new BranchItem();
                        branchItem.setItem(item);
                        branchItem.setBranch(branch);
                        branchItem.setIsActive(itemDto.getIsActive());

                        itemBranchRepo.save(branchItem);
                    }
                }

                return "Success created";

            } else {
                return "category is invalid";
            }
        } else {

            return "user is invalid";
        }

    }

    @Override
    @Transactional
    public String update(ItemDto itemDto,Integer itemId) {

        User user = userRepository.findByUserName(itemDto.getCreatedUser());

        if (user != null) {

            Category category = categoryRepository.findById(itemDto.getCategoryId()).get();

            if (category != null) {


                Item item = itemRepo.findById(itemId).get();

                item.setName(itemDto.getName());
                item.setDescription(itemDto.getDescription());
                item.setCreatedDate(new Date());
                item.setIsActive(itemDto.getIsActive());
                item.setOriginalPrice(itemDto.getOriginalPrice());
                item.setSellingPrice(itemDto.getSellingPrice());
                item.setCreatedUser(user);
                item.setCategory(category);
                itemRepo.save(item);

                itemBranchRepo.deleteAllByItem_Id(itemId);

                for (Integer id:itemDto.getBranchId()){

                    Branch branch = branchRepository.findById(id).get();

                    if(branch!= null){

                        BranchItem branchItem = new BranchItem();
                        branchItem.setItem(item);
                        branchItem.setBranch(branch);
                        branchItem.setIsActive(itemDto.getIsActive());

                        itemBranchRepo.save(branchItem);
                    }
                }

                return "Success updated";

            } else {
                return "category is invalid";
            }
        } else {

            return "user is invalid";
        }

    }

    @Override
    public List<Item> getAll() {
        return itemRepo.findAll();
    }

    @Override
    public List<BranchItem> getBranchByItem(Integer itemId) {
        return itemBranchRepo.findAllByItem_Id(itemId);
    }

    @Override
    public List<BranchItem> getItemByBranch(Integer branchId) {
        return itemBranchRepo.findAllByBranch_Id(branchId);
    }

    @Override
    public List<BranchItem> getBranchByItemByCategory(Integer id, Integer categoryId) {
        return itemBranchRepo.findAllByBranch_IdAndItem_Category_IdAndIsActive(id,categoryId,true);
    }

    @Override
    public String updateBranchItemStatus(Integer branchId, Integer itemId,Boolean status) {

        BranchItem branchItem = itemBranchRepo.findByBranch_IdAndItem_Id(branchId,itemId);

        branchItem.setIsActive(status);
        itemBranchRepo.save(branchItem);

        return "Successfully update item status";

    }
}
