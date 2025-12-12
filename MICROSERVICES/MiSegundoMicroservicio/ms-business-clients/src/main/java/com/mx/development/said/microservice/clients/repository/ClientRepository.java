package com.mx.development.said.microservice.clients.repository;

import com.mx.development.said.microservice.clients.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findByStatus(String status);

}
