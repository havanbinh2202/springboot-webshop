    package com.tn.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    @Data
    @Entity
    @Table(name = "product")
    public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private int id;

        private String productname;

        private int price;

        private String Image;

        @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
        @JoinColumn(name = "category_id")
        private Category category;
    }
