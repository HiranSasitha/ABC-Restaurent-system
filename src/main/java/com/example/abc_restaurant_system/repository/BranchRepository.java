package com.example.abc_restaurant_system.repository;

import com.example.abc_restaurant_system.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BranchRepository extends JpaRepository<Branch,Integer> {

    boolean existsByName(String name);

    List<Branch> findAllByIsActive(Boolean isActive);
}
