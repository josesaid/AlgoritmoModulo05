package com.mx.development.said.microservice.orders.controller;

import com.mx.development.said.microservice.orders.dto.RequestOrder;
import com.mx.development.said.microservice.orders.dto.ResponseOrder;
import com.mx.development.said.microservice.orders.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<ResponseOrder> createOrder(@RequestBody RequestOrder order){
        //log.info("Creating order: {}", order);
        ResponseOrder response = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{orderId}")
    public ResponseOrder getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }


    @GetMapping("/query")
    public ResponseEntity<List<ResponseOrder>> getOrderByStatus(@RequestParam String status) {

        log.info("clase: {}", OrderController.class.getClass().getSimpleName());
        List<ResponseOrder> orders = orderService.findByStatus(status);

        if (orders == null || orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(orders);
    }



    @GetMapping("/")
    public ResponseEntity<List<ResponseOrder>> getAllOrders() {
        List<ResponseOrder> orders = orderService.findAll();

        if (orders == null || orders.isEmpty()) {
            log.warn("No orders found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        log.info("Number of orders found: {}", orders.size());
        return ResponseEntity.ok(orders);
    }


}
