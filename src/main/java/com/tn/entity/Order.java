package com.tn.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name="orderid", length = 12)
    private String orderId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="orderdate")
    private Date orderDate=new Date();

    @Column(name="receivename", length = 100)
    private String receiveName;

    @Column(name="receiveaddress", length = 100)
    private String receiveAddress;

    @Column(name="receivephone", length = 100)
    private String receivePhone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name="note",length = 1000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> details=new HashSet<OrderDetail>();
}
