package com.mx.development.said.microservice.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("El producto con id: " + id +" no existe");
    }

}
