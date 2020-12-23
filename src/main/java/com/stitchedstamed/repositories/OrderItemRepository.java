package com.stitchedstamed.repositories;

import com.stitchedstamed.models.OrderItem;
import com.stitchedstamed.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orderItems", path = "orderItems")
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi FROM OrderItem oi WHERE oi.status = 'In-Progress' AND oi.purchaseOrder.id = :purchaseOrderId AND oi.monogram.id is not null")
    List<OrderItem> findAllMonogramByInProgressStatusAndPurchaseOrderId(@Param("purchaseOrderId") Long purchaseOrderId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.status = 'In-Progress' AND oi.purchaseOrder.id = :purchaseOrderId AND oi.specialOrder.id is not null")
    List<OrderItem> findAllSpecialOrderByInProgressStatusAndPurchaseOrderId(@Param("purchaseOrderId") Long purchaseOrderId);

    Integer countAllByPurchaseOrder(PurchaseOrder purchaseOrder);
}
