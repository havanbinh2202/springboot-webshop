package com.tn.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orderdetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderdetailid")
    private Long orderDetailId;

    @Column(name="price")
    private float price;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderid",nullable = false)
    private Order order=new Order();

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
    // Constructor
    public OrderDetail(float price, int quantity, Product product, Order order) {
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public OrderDetail() {

    }
}
