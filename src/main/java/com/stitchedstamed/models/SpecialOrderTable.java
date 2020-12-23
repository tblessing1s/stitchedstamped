package com.stitchedstamed.models;

import lombok.Data;

@Data
public class SpecialOrderTable {
    Long orderId;
    String itemName;
    String brand;
    String size;
    String itemColor;
    String customer;
}
