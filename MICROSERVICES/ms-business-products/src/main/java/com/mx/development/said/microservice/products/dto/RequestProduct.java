package com.mx.development.said.microservice.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProduct {
    private String name;
    private String description;
    private String price;
    private String status;
}
