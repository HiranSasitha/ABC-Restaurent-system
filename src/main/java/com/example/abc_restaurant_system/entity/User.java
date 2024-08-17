package com.example.abc_restaurant_system.entity;


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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name",length = 100)
    private String userName;

    @Column(name = "user_first_name",length = 100)
    private String userFirstName;
    @Column(name = "user_last_name",length = 100)
    private String userLastName;
    private String password;

    private String email;

    private String contactNumb;

    private String address;
    @Column(name = "created_date",columnDefinition = "DATETIME")
    private Date createdDate;
    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private Boolean isActive;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "createdUser")
    private Set<Category> categories;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "createdUser")
    private Set<Item> items;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "createdUser")
    private Set<Branch> branches;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "User_Role",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }

    )
    private Set<Role> roles;
}
