package com.example.abc_restaurant_system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "branch_category")
public class BranchCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "branch_id",nullable = false)
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
}
