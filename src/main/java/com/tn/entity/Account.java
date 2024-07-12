package com.tn.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

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

//    private String role;
    @Column(nullable = false) // Make role mandatory
//    private String role; // Default role
    private String role = "ROLE_ADMIN"; // Default role
    private Date createdAt;

    private Date updatedAt;

    @OneToOne(mappedBy = "account")
    @ToString.Exclude
    private Cart cart;

    @Override
    public int hashCode() {
        // Tính toán hashCode dựa trên id hoặc username, bỏ qua cart
        return Objects.hash(id, username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(username, account.username);
    }
}
