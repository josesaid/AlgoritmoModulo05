package com.mx.development.said.microservice.repository;

import com.mx.development.said.microservice.dto.ResponseProduct;
import com.mx.development.said.microservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByStatus(String status);
}
