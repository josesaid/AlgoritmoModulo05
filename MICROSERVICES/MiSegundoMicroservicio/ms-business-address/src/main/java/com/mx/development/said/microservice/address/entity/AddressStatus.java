package com.mx.development.said.microservice.address.entity;

public enum AddressStatus {
    ACTIVE,        // Dirección válida y utilizable (default)
    INACTIVE,      // Dirección deshabilitada temporalmente
    PENDING,       // Dirección registrada pero no validada
    INVALID,       // Dirección incorrecta o no localizable
    DELETED        // Eliminada lógicamente (soft delete)
}