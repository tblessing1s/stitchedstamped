package com.stitchedstamed.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String signature;
    String status;
    @OneToOne
    PurchaseOrder purchaseOrder;
    @ManyToOne
    Monogram monogram;
    @ManyToOne
    SpecialOrder specialOrder;
}
