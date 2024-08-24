package com.example.abc_restaurant_system.controller;

import com.example.abc_restaurant_system.dto.BranchDto;
import com.example.abc_restaurant_system.dto.CreateUserDto;
import com.example.abc_restaurant_system.entity.User;
import com.example.abc_restaurant_system.service.DataInitializer;
import com.example.abc_restaurant_system.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private DataInitializer dataInitializer;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void setDataInitialize(){
        dataInitializer.dataInitials();
    }

    @PostMapping("/register-new-user")
    public ResponseEntity<?> registerNewUser(@RequestBody CreateUserDto user) {

        JSONObject jsonObject = new JSONObject();
        String msg = userService.saveCustomer(user);

        jsonObject.put("msg",msg);

        if (msg.equals("Success Register")) {

            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){

        List<User> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/update/{id}/{isActive}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUserStatus(@PathVariable Integer id,@PathVariable Boolean
                                              isActive){
        JSONObject jsonObject = new JSONObject();

        String msg = userService.updateStatus(id,isActive);

        jsonObject.put("msg",msg);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.CREATED);
    }
}
