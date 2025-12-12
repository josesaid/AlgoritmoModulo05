package com.mx.development.said.microservice.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStore {
    private String orderId;
    private String productId;
    private String clientId;
    private String addressId;
    private String status;
    private String quantity;
}
