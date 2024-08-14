package com.example.abc_restaurant_system.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "created_date",columnDefinition = "DATETIME")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "created_user_id",nullable = false)

    private User createdUser;

    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private Boolean isActive;
}
