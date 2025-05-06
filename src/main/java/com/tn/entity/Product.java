    package com.tn.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.util.HashSet;
    import java.util.Set;

    @Data
    @Entity
    @Table(name = "product")
    public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long productId;

        private String productname;

        private int price;

        private String image;

        @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
        @JoinColumn(name = "categoryId")
        private Category category;

        @OneToMany(mappedBy = "product")
        private Set<OrderDetail> details=new HashSet<OrderDetail>();

    }
