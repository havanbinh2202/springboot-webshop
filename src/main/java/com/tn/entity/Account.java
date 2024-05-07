package com.tn.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String username;

    private String password;

    private String email;

    private Date createdAt;

    private Date updatedAt;
}
