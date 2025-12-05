package com.mx.development.said.microservice.orders.advices;

import com.mx.development.said.microservice.orders.exception.OrderNotFoundException;
import com.mx.development.said.microservice.orders.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFound(ProductNotFoundException ex) {
        Map<String, Object> error1 = new HashMap<>();
        error1.put("error", ex.getMessage());
        error1.put("timestamp", LocalDateTime.now());
        error1.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error1, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOrderNotFound(OrderNotFoundException ex) {
        Map<String, Object> error2 = new HashMap<>();
        error2.put("error", ex.getMessage());
        error2.put("timestamp", LocalDateTime.now());
        error2.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error2, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String, Object>> handleHttpClientErrorException(HttpClientErrorException ex) {
        Map<String, Object> error3 = new HashMap<>();
        error3.put("error", "Al crear la orden, El producto dado no existe en el microservicio remoto de productos");
        error3.put("timestamp", LocalDateTime.now());
        error3.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(error3, HttpStatus.NOT_FOUND);
    }


}
