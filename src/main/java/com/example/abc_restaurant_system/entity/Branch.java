package com.example.abc_restaurant_system.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Branch {

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


    @ManyToMany
    @JoinTable(
            name = "branch_item",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )

    private Set<Item> items = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "branch_category",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )

    private Set<Category> categories = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "branch_user",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id_id")
    )

    private Set<User> users = new HashSet<>();
}
