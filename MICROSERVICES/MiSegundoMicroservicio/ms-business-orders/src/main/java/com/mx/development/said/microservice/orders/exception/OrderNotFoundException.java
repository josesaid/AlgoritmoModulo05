package com.mx.development.said.microservice.orders.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id) {
        super("La orden con id: " + id +" no existe");
    }

}
