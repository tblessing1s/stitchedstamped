package com.stitchedstamed.repositories;

import com.stitchedstamed.models.OrderItem;
import com.stitchedstamed.models.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "purchase-orders", path = "purchase-orders")
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    @Query("SELECT po FROM PurchaseOrder po WHERE po.status = 'In-Progress'")
    List<PurchaseOrder> findAllByInProgressStatus();
}
