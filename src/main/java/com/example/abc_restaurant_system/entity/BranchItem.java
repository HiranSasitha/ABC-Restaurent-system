package com.example.abc_restaurant_system.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "branch_item")
public class BranchItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "branch_id",nullable = false)
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private Item item;
}
