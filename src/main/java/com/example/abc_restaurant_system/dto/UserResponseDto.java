package com.example.abc_restaurant_system.dto;


import com.example.abc_restaurant_system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String jwtToken;
    private User user;

}
