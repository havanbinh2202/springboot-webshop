package com.tn.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int categoryId;

    @Column(name = "categoryname", length = 255)
    private String categoryname;


    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
