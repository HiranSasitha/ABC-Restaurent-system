package com.example.abc_restaurant_system.repository;

import com.example.abc_restaurant_system.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
}
