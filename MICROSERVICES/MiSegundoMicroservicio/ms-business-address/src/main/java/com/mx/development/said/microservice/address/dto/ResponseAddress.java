package com.mx.development.said.microservice.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAddress {
    private Long id;
    private String street;
    private String country;
    private String createdAt;
    private String updatedAt;
    private String status;
}
//lo que se recibe