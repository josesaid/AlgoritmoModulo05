package com.mx.development.said.microservice.dto;

import com.mx.development.said.microservice.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {
    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private ProductStatus status;
}
