package com.example.abc_restaurant_system.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;
    private String description;
    @Column(name = "created_date",columnDefinition = "DATETIME")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "created_user_id",nullable = false)

    private User createdUser;

    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private Boolean isActive;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
    @JsonIgnore
    private Set<Item> items;
}
