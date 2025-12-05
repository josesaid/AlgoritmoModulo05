package com.mx.development.said.microservice.orders.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseOrder {
    private Long id;
    private String productId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
