package com.example.abc_restaurant_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_place")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date",columnDefinition = "DATETIME")
    private Date createdDate;


    @Column(nullable = false)
    private Double total;

    @Column(nullable = false,name = "tax(%)")
    private Double tax;

    @Column(name ="discount")
    private Double discount;

    @Column(name = "pay_option")
    private String payOption;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User userEntity;
}
