package com.mx.development.said.microservice.clients.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(length = 30)
    private String email;

    @Column(length = 20)
    @Pattern(regexp = "^[0-9\\-+]{8,15}$")
    private String phoneNumber;

    @Column(length = 10)
    private String status;
}
