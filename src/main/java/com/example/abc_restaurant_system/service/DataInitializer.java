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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DataInitializer {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BranchRepository branchRepository;


@Transactional
        public void  dataInitials() {
            if (userRoleRepository.findByRoleName("ROLE_ADMIN").isEmpty()) {
                Role role = new Role();
                role.setRoleName("ROLE_ADMIN");
                role.setRoleDescription("admin");
                userRoleRepository.save(role);
            }
            if (userRoleRepository.findByRoleName("ROLE_BRANCHADMIN").isEmpty()) {
                Role role = new Role();
                role.setRoleName("ROLE_BRANCHADMIN");
                role.setRoleDescription("branch-admin");
                userRoleRepository.save(role);
            }
            if (userRoleRepository.findByRoleName("ROLE_USER").isEmpty()) {
                Role role = new Role();
                role.setRoleName("ROLE_USER");
                role.setRoleDescription("user");
                userRoleRepository.save(role);
            }

            if (!userRepository.existsByUserName("admin")) {
                User user = new User();
                Optional<Role> role = userRoleRepository.findByRoleName("ROLE_ADMIN");
                Set<Role> roles = new HashSet<>();
                if (role.isPresent()) {
                    roles.add(role.get());
                }
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
                user.setRoles(roles);

                userRepository.save(user);
            }

            if (!branchRepository.existsByName("Head Office")) {
                Branch branch = new Branch();
                branch.setName("Head Office");
                branch.setCreatedUser(userRepository.findByUserName("admin"));
                branch.setCreatedDate(new Date());
                branch.setIsActive(true);
                branchRepository.save(branch);
            }


        }

}
