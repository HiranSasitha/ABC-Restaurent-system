package com.example.abc_restaurant_system.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class orderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private Boolean isStatus;

    @OneToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
}
