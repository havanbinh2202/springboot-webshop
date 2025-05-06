package com.tn.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) // Make role mandatory
    private Role role = Role.ROLE_USER; // Default role
    private Date createdAt;

    private Date updatedAt;
    @ToString.Exclude
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<Order> orders=new HashSet<Order>();

}
