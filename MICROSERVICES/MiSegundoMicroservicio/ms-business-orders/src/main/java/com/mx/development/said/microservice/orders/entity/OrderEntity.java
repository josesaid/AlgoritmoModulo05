package com.mx.development.said.microservice.orders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productId;

    @Column
    private String clientId;

    @Column
    private String addressId;

    @Column(length = 20)
    private String status;

    @Column(length = 5)
    private String quantity;

    private String createdAt;

    private String updatedAt;

}
