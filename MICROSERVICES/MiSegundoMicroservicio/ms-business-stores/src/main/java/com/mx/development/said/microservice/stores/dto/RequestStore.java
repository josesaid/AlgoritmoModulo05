package com.mx.development.said.microservice.stores.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestStore {
    private String orderId;
    private String productId;
    private String clientId;
    private String addressId;
    private String status;
    private String createdAt;
    private String updatedAt;
    private String quantity;
}
