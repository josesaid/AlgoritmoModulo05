package com.mx.development.said.microservice.orders.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String id) {
        super(id);
    }

}
