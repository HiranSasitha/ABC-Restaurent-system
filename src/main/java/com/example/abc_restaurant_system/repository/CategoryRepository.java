package com.example.abc_restaurant_system.repository;

import com.example.abc_restaurant_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByName(String name);

    List<Category> findAllByIsActive(Boolean isActive);
}
