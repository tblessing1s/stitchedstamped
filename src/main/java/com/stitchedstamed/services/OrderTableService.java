package com.stitchedstamed.services;

import com.stitchedstamed.models.*;
import com.stitchedstamed.repositories.CustomerRepository;
import com.stitchedstamed.repositories.MonogramRepository;
import com.stitchedstamed.repositories.OrderItemRepository;
import com.stitchedstamed.repositories.SpecialOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderTableService {
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final MonogramRepository monogramRepository;
    private final SpecialOrderRepository specialOrderRepository;

    @Autowired
    public OrderTableService(OrderItemRepository orderItemRepository, CustomerRepository customerRepository, MonogramRepository monogramRepository, SpecialOrderRepository specialOrderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.monogramRepository = monogramRepository;
        this.specialOrderRepository = specialOrderRepository;
    }

    public List<MonogramTable> createMonogramTable(Long purchaseOrderId) {
        List<MonogramTable> monogramTableData = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepository.findAllMonogramByInProgressStatusAndPurchaseOrderId(purchaseOrderId);

        orderItems.forEach(orderItem -> {
            MonogramTable monogramTable = new MonogramTable();
            monogramTable.setOrderId(orderItem.getId());
            Customer customer = customerRepository.getOne(orderItem.getPurchaseOrder().getCustomer().getId());
            Monogram monogram = monogramRepository.getOne(orderItem.getMonogram().getId());
            monogramTable.setCustomer(customer.getFirstName() + " " + customer.getLastName());
            monogramTable.setItemName(monogram.getItemName());
            monogramTable.setMonogram(monogram.getMonogram());
            monogramTableData.add(monogramTable);
        });

        return monogramTableData;
    }


    public List<SpecialOrderTable> createSpecialOrderTable(Long purchaseOrderId) {
        List<SpecialOrderTable> specialOrderTableData = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepository.findAllSpecialOrderByInProgressStatusAndPurchaseOrderId(purchaseOrderId);

        orderItems.forEach(orderItem -> {
            SpecialOrderTable specialOrderTable = new SpecialOrderTable();
            specialOrderTable.setOrderId(orderItem.getId());
            Customer customer = customerRepository.getOne(orderItem.getPurchaseOrder().getCustomer().getId());
            SpecialOrder specialOrder = specialOrderRepository.getOne(orderItem.getSpecialOrder().getId());
            specialOrderTable.setItemName(specialOrder.getItemName());
            specialOrderTable.setCustomer(customer.getFirstName() + " " + customer.getLastName());
            specialOrderTable.setBrand(specialOrder.getBrand());
            specialOrderTable.setSize(specialOrder.getSize());
            specialOrderTable.setItemColor(specialOrder.getItemColor());
            specialOrderTableData.add(specialOrderTable);
        });

        return specialOrderTableData;
    }
}
