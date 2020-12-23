package com.stitchedstamed.models;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @OneToOne
    Customer customer;
    OrderSource orderSource;
    String status;

    @Column(name = "create_ts")
    @Generated(GenerationTime.INSERT)
    private String create_ts;
//
//    @CreatedDate
//    String create_ts;
}
