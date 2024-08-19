package com.example.abc_restaurant_system.service.impl;

import com.example.abc_restaurant_system.dto.CreateUserDto;
import com.example.abc_restaurant_system.entity.Branch;
import com.example.abc_restaurant_system.entity.Role;
import com.example.abc_restaurant_system.entity.User;
import com.example.abc_restaurant_system.repository.BranchRepository;
import com.example.abc_restaurant_system.repository.UserRepository;
import com.example.abc_restaurant_system.repository.UserRoleRepository;
import com.example.abc_restaurant_system.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private BranchRepository branchRepository ;

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public String saveCustomer(CreateUserDto user) {
        String msg = "";
        User user1 = new User();
        JSONObject jsonObject = new JSONObject();

        if(!userRepository.existsByUserName(user.getUserName())){
           Role role = userRoleRepository.findById(user.getRoleId()).get();

           Branch branch = new Branch();

           if(user.getBranchId() != -1){

               branch = branchRepository.findById(user.getBranchId()).get();
           }else {
               branch = null;
           }

            Set<Role> roles = new HashSet<>();
            roles.add(role);
            String hashPassword = passwordEncoder.encode(user.getPassword());
            user1.setRoles(roles);
            user1.setBranch(branch);
            user1.setUserName(user.getUserName());
            user1.setEmail(user.getEmail());
            user1.setAddress(user.getAddress());
            user1.setUserLastName(user.getUserLastName());
            user1.setUserFirstName(user.getUserFirstName());
            user1.setContactNumb(user.getContactNumb());
            user1.setPassword(hashPassword);
            user1.setIsActive(true);
            user1.setCreatedDate(new Date());
            userRepository.save(user1);

            msg = "Success Register";


            return msg;
        }else {
            msg = "User name is exists";
            user1 = null;

            jsonObject.put("msg",msg);
            jsonObject.put("user",user1);
            return msg;
        }


    }
}
