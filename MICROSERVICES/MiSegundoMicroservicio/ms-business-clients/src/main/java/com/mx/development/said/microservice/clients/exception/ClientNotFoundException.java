package com.mx.development.said.microservice.clients.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(Long id) {
        super("El cliente con id: " + id +" no existe");
    }

}
