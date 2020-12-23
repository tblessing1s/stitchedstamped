package com.stitchedstamed.services;

import com.stitchedstamed.models.Monogram;
import com.stitchedstamed.models.MonogramTable;
import com.stitchedstamed.models.OrderItem;
import com.stitchedstamed.models.SpecialOrder;
import com.stitchedstamed.repositories.CustomerRepository;
import com.stitchedstamed.repositories.MonogramRepository;
import com.stitchedstamed.repositories.OrderItemRepository;
import com.stitchedstamed.repositories.SpecialOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final MonogramRepository monogramRepository;
    private final SpecialOrderRepository specialOrderRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, CustomerRepository customerRepository, MonogramRepository monogramRepository, SpecialOrderRepository specialOrderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.monogramRepository = monogramRepository;
        this.specialOrderRepository = specialOrderRepository;
    }

    public List<Monogram> getMonogramsByPurchaseOrder(Long purchaseOrderId) {
        List<Monogram> monogramData = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepository.findAllMonogramByInProgressStatusAndPurchaseOrderId(purchaseOrderId);

        orderItems.forEach(orderItem -> {
            monogramData.add(this.monogramRepository.getOne(orderItem.getPurchaseOrder().getId()));
        });

        return monogramData;
    }

    public List<SpecialOrder> getSpecialOrdersByPurchaseOrder(Long purchaseOrderId) {
        List<SpecialOrder> specialOrderData = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepository.findAllSpecialOrderByInProgressStatusAndPurchaseOrderId(purchaseOrderId);

        orderItems.forEach(orderItem -> {
            specialOrderData.add(this.specialOrderRepository.getOne(orderItem.getPurchaseOrder().getId()));
        });

        return specialOrderData;
    }
}
