package com.mx.development.said.microservice.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StoreMessageItem {
    private String orderId;
    private String productId;
    private String clientId;
    private String addressId;
    private String status;
    private String createdAt;
    private String updatedAt;
    private String quantity;
}
