package com.mx.development.said.microservice.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStore {
    private Long id;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String status;
    private String price;
    private String quantity;
}
