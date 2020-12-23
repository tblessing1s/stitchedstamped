package com.stitchedstamed.controllers;

import com.stitchedstamed.models.Monogram;
import com.stitchedstamed.models.MonogramTable;
import com.stitchedstamed.models.SpecialOrder;
import com.stitchedstamed.services.OrderItemService;
import com.stitchedstamed.services.PurchaseOrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orderItems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public  OrderItemController (OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/purchaseOrder/{purchaseOrderId}/monograms")
    @ResponseBody
    public ResponseEntity<List<Monogram>> getAllMonogramOrders(@PathVariable Long purchaseOrderId) {
        List<Monogram> monogramData;
        try{
            monogramData = this.orderItemService.getMonogramsByPurchaseOrder(purchaseOrderId);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(monogramData, HttpStatus.OK);
    }

    @GetMapping("/purchaseOrder/{purchaseOrderId}/specialOrders")
    @ResponseBody
    public ResponseEntity<List<SpecialOrder>> getAllSpecialOrders(@PathVariable Long purchaseOrderId) {
        List<SpecialOrder> specialOrderData;
        try{
            specialOrderData = this.orderItemService.getSpecialOrdersByPurchaseOrder(purchaseOrderId);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(specialOrderData, HttpStatus.OK);
    }
}
