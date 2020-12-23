package com.stitchedstamed.models;

import lombok.Data;

@Data
public class MonogramTable {
    Long orderId;
    String itemName;
    String monogram;
    String customer;
}
