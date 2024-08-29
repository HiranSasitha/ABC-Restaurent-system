package com.example.abc_restaurant_system.repository;

import com.example.abc_restaurant_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("SELECT o FROM Order o WHERE o.branch.id = :branchId AND o.createdDate BETWEEN :startOfDay AND :endOfDay")
    List<Order> findAllByBranchIdAndCreatedDate(@Param("branchId") Integer branchId, @Param("startOfDay") Date startOfDay, @Param("endOfDay") Date endOfDay);

}
