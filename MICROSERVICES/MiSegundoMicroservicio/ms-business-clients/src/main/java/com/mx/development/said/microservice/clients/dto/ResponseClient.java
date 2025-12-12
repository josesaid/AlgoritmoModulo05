package com.mx.development.said.microservice.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClient {
    private Long id;
    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;
    private String status;
}
