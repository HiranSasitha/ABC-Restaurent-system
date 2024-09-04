package com.example.abc_restaurant_system.dto;

import com.example.abc_restaurant_system.entity.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
@Data
public class BranchDto {


    private String name;
    private String location;

    private String createdUser;

    private Integer seat;

    private Boolean isActive;

    private List<Integer> itemId;
}
