package com.example.abc_restaurant_system.repository;

import com.example.abc_restaurant_system.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Integer> {

    List<OrderStatus> findAllByOrder_UserEntity_Id(Integer id);
}
