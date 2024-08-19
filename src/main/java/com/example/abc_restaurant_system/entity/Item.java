package com.example.abc_restaurant_system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    private String description;

    @Column(nullable = false)
    private Double originalPrice;

    @Column(nullable = false)
    private Double sellingPrice;

    @Column(nullable = false)
    private Double discount;



    @Column(name = "created_date",columnDefinition = "DATETIME")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "created_user_id",nullable = false)

    private User createdUser;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)

    private Category category;

    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private Boolean isActive;


}
