package com.stitchedstamed.models;

import lombok.Data;

@Data
public class PurchaseOrderTable {
    Long purchaseOrderId;
    String customer;
    Integer orderCount;
    String orderDate;
}
