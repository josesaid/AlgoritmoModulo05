package com.mx.development.said.microservice.stores.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stores")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, unique = true)
    private Long orderId;

    @Column(length = 8, unique = true)
    private Long productId;

    @Column(length = 8)
    private Long quantity;

    @Column
    private Long clientId;

    @Column
    private Long addressId;

    @Column(length = 20)
    private String phone;

    @Column(length = 200)
    private String description;

    private String status;

}
