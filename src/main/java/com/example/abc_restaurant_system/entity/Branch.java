package com.example.abc_restaurant_system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;
    @Column(name = "seat")
    private Integer seat;
    @Column(name = "created_date",columnDefinition = "DATETIME")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "created_user_id",nullable = false)

    private User createdUser;

    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private Boolean isActive;




}
