package com.example.abc_restaurant_system.repository;

import com.example.abc_restaurant_system.entity.BranchItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemBranchRepo extends JpaRepository<BranchItem,Integer> {


    void deleteAllByItem_Id(Integer id);
}
