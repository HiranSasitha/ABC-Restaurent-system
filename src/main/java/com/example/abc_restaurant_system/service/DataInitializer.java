package com.example.abc_restaurant_system.service;

import com.example.abc_restaurant_system.entity.Branch;
import com.example.abc_restaurant_system.entity.Role;
import com.example.abc_restaurant_system.entity.User;
import com.example.abc_restaurant_system.repository.BranchRepository;
import com.example.abc_restaurant_system.repository.UserRepository;
import com.example.abc_restaurant_system.repository.UserRoleRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Configuration
public class DataInitializer {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BranchRepository branchRepository;


    @Bean
    CommandLineRunner initRoles() {
        return args -> {
            if (userRoleRepository.findByRoleName("ROLE_ADMIN").isEmpty()) {
                Role role = new Role();
                role.setRoleName("ROLE_ADMIN");
                role.setRoleDescription("admin in all branch");
                userRoleRepository.save(role);
            }
            if (userRoleRepository.findByRoleName("ROLE_BRANCHADMIN").isEmpty()) {
                Role role = new Role();
                role.setRoleName("ROLE_BRANCHADMIN");
                role.setRoleDescription("admin in branch");
                userRoleRepository.save(role);
            }
            if (userRoleRepository.findByRoleName("USER").isEmpty()) {
                Role role = new Role();
                role.setRoleName("USER");
                role.setRoleDescription("customer in branch");
                userRoleRepository.save(role);
            }

            if (!userRepository.existsByUserName("admin")) {
                User user = new User();
                String hasPassword = passwordEncoder.encode("a");
                user.setUserName("admin");
                user.setBranch(null);
                user.setUserFirstName("Admin");
                user.setUserLastName("test");
                user.setAddress("test");
                user.setEmail("admin@gmail.com");
                user.setContactNumb("0764899375");
                user.setCreatedDate(new Date());
                user.setIsActive(true);
                user.setPassword(hasPassword);

                userRepository.save(user);
            }

            if(!branchRepository.existsByName("Head Office")){
                Branch branch = new Branch();
                branch.setName("Head Office");
                branch.setCreatedUser(userRepository.findByUserName("admin"));
                branch.setCreatedDate(new Date());
                branch.setIsActive(true);
            }

        };
    }

}
