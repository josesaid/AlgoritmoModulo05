package com.mx.development.said.microservice.repository;

import com.mx.development.said.microservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
