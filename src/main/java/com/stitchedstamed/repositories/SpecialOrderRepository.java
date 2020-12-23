package com.stitchedstamed.repositories;

import com.stitchedstamed.models.SpecialOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "specialOrders", path = "specialOrders")
public interface SpecialOrderRepository extends JpaRepository<SpecialOrder, Long> {
}
