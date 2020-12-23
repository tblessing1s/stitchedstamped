package com.stitchedstamed.controllers;

import com.stitchedstamed.models.PurchaseOrderTable;
import com.stitchedstamed.services.PurchaseOrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/purchaseOrderTable")
public class PurchaseOrderTableController {
    private final PurchaseOrderTableService purchaseOrderTableService;

    @Autowired
    public  PurchaseOrderTableController (PurchaseOrderTableService purchaseOrderTableService) {
        this.purchaseOrderTableService = purchaseOrderTableService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<PurchaseOrderTable>> getAllPurchaseOrders() {
        List<PurchaseOrderTable> purchaseOrderTables;
        try {
            purchaseOrderTables = this.purchaseOrderTableService.createPurchaseOrderTable();
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(purchaseOrderTables, HttpStatus.OK);
    }

    @PostMapping("/purchaseOrder/{purchaseOrderId}")
    @ResponseBody
    public ResponseEntity<List<PurchaseOrderTable>> updateOrderToFinish(
            @PathVariable Long purchaseOrderId
    ) {
        List<PurchaseOrderTable> purchaseOrderTables;
        try{
            purchaseOrderTables = this.purchaseOrderTableService.updatePurchaseOrderToFinish(purchaseOrderId);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(purchaseOrderTables, HttpStatus.OK);
    }
}
