package com.stitchedstamed.services;

import com.stitchedstamed.models.*;
import com.stitchedstamed.repositories.CustomerRepository;
import com.stitchedstamed.repositories.OrderItemRepository;
import com.stitchedstamed.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderTableService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public PurchaseOrderTableService(
            PurchaseOrderRepository purchaseOrderRepository,
            OrderItemRepository orderItemRepository,
            CustomerRepository customerRepository
    ) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
    }

    public List<PurchaseOrderTable> createPurchaseOrderTable() {
        List<PurchaseOrderTable> purchaseOrderTableData = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = this.purchaseOrderRepository.findAllByInProgressStatus();

        purchaseOrders.forEach(purchaseOrder -> {
            PurchaseOrderTable purchaseOrderTable = new PurchaseOrderTable();
            purchaseOrderTable.setPurchaseOrderId(purchaseOrder.getId());
            Customer customer = this.customerRepository.getOne(purchaseOrder.getCustomer().getId());
            Integer orderItemCount = this.orderItemRepository.countAllByPurchaseOrder(purchaseOrder);
            purchaseOrderTable.setCustomer(customer.getFirstName() + " " + customer.getLastName());
            purchaseOrderTable.setOrderCount(orderItemCount);
            purchaseOrderTable.setOrderDate(purchaseOrder.getCreate_ts());
            purchaseOrderTableData.add(purchaseOrderTable);
        });

        return purchaseOrderTableData;
    }

    public List<PurchaseOrderTable> updatePurchaseOrderToFinish(Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(purchaseOrderId);
        purchaseOrder.setStatus("Finished");
        purchaseOrderRepository.save(purchaseOrder);

        return createPurchaseOrderTable();
    }
}
