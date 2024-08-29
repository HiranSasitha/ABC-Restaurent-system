package com.example.abc_restaurant_system.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_place")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date",columnDefinition = "DATETIME")
    private Date createdDate;

    @Column(nullable = false)
    private Double total;

    @Column(name = "order_option")
    private String orderOption;
    @Column(name = "booking_seat")
    private Integer bookingSeat;

    @Column(name = "pay_option")
    private String payOption;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User userEntity;

    @ManyToOne
    @JoinColumn(name = "branch_id",nullable = false)
    private Branch branch;
}
