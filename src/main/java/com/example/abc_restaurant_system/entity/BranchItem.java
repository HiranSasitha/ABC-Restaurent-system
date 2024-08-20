package com.example.abc_restaurant_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
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


    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private Boolean isActive;
}
