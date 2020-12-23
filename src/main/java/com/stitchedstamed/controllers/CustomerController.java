package com.stitchedstamed.controllers;

import com.stitchedstamed.models.Customer;
import com.stitchedstamed.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;

    }

    @GetMapping("/purchaseOrder/{purchaseOrderId}")
    @ResponseBody
    public ResponseEntity<Customer> getCustomerByPurchaseOrderId(
            @PathVariable Long purchaseOrderId
    ) {
        Customer customer;
        try {
            customer = this.customerService.getCustomerByPurchaseOrderId(purchaseOrderId);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
