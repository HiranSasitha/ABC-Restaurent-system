package com.example.abc_restaurant_system.repository;

import com.example.abc_restaurant_system.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,Integer> {
}
