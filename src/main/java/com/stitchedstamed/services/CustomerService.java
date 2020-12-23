package com.stitchedstamed.services;

import com.stitchedstamed.models.Customer;
import com.stitchedstamed.models.OrderItem;
import com.stitchedstamed.models.PurchaseOrder;
import com.stitchedstamed.repositories.CustomerRepository;
import com.stitchedstamed.repositories.OrderItemRepository;
import com.stitchedstamed.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           PurchaseOrderRepository purchaseOrderRepository
                           ) {
        this.customerRepository = customerRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public Customer getCustomerByPurchaseOrderId(Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = this.purchaseOrderRepository.findById(purchaseOrderId).orElse(null);

        return this.customerRepository.findById(Objects.requireNonNull(purchaseOrder).getCustomer().getId()).orElse(null);
    }
}
