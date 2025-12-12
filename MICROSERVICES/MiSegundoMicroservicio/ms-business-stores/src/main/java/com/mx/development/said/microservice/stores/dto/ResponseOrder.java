package com.mx.development.said.microservice.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrder {
    private Long id;
    private String productId;
    private String clientId;
    private String addressId;
    private String status;
    private String quantity;
    private String createdAt;
    private String updatedAt;
}
