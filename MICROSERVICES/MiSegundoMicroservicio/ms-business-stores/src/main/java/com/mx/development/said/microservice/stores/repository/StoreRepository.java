package com.mx.development.said.microservice.stores.repository;

import com.mx.development.said.microservice.stores.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    Optional<StoreEntity> findByOrderId(Long orderId);
    Optional<StoreEntity> findByProductId(Long productId);
    Optional<StoreEntity> findByClientId(Long clientId);
    Optional<StoreEntity> findByAddressId(Long addressId);
    List<StoreEntity> findByDescriptionContaining(String description);
    List<StoreEntity> findByPhone(String phone);
    List<StoreEntity> findByQuantity(int quantity);
    List<StoreEntity> findByStatus(String status);
}
