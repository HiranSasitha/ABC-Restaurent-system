package com.example.abc_restaurant_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class createUserDto {

    private String userName;


    private String userFirstName;

    private String userLastName;
    private String password;

    private String email;

    private String contactNumb;

    private String address;
}
