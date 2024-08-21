package com.example.abc_restaurant_system.repository;

import com.example.abc_restaurant_system.entity.BranchItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemBranchRepo extends JpaRepository<BranchItem,Integer> {


    void deleteAllByItem_Id(Integer id);

    void deleteAllByBranch_Id(Integer id);

    List<BranchItem> findAllByItem_Id(Integer itemId);

    List<BranchItem> findAllByBranch_Id(Integer branch);
}
