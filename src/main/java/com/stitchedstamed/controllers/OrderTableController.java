package com.stitchedstamed.controllers;

import com.stitchedstamed.models.MonogramTable;
import com.stitchedstamed.models.SpecialOrderTable;
import com.stitchedstamed.services.OrderTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/orderTable")
public class OrderTableController {
    private final OrderTableService orderTableService;

    @Autowired
    public OrderTableController (OrderTableService orderTableService) {
        this.orderTableService = orderTableService;
    }

    @GetMapping("/purchaseOrder/{purchaseOrderId}/monogram-table")
    @ResponseBody
    public ResponseEntity<List<MonogramTable>> getAllMonogramOrders(@PathVariable Long purchaseOrderId) {
        List<MonogramTable> monogramTableData;
        try{
            monogramTableData = this.orderTableService.createMonogramTable(purchaseOrderId);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(monogramTableData, HttpStatus.OK);
    }

    @GetMapping("/purchaseOrder/{purchaseOrderId}/specialOrder-table")
    @ResponseBody
    public ResponseEntity<List<SpecialOrderTable>> getAllSpecialOrders(@PathVariable Long purchaseOrderId) {
        List<SpecialOrderTable> specialOrderTableData;
        try{
            specialOrderTableData = this.orderTableService.createSpecialOrderTable(purchaseOrderId);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(specialOrderTableData, HttpStatus.OK);
    }
}
