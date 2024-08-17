package com.example.abc_restaurant_system.repository;

import com.example.abc_restaurant_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer> {

    @Override
    Optional<User> findById(Integer integer);

    User findByUserName(String userName);

    boolean existsByUserName(String userName);
}
