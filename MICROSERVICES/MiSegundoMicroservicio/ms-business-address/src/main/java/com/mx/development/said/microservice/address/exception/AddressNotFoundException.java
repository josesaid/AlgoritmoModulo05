package com.mx.development.said.microservice.address.exception;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(Long id) {
        super("El direccion con id: " + id +" no existe");
    }

}
