package com.mx.development.said.microservice.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestClient {
    private String name;
    private String email;
    private String phoneNumber;
    private String status;
}
