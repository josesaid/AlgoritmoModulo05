package com.mx.development.said.microservice.orders.config;

import com.mx.development.said.microservice.orders.entity.OrderEntity;
import com.mx.development.said.microservice.orders.entity.OrderStatus;
import com.mx.development.said.microservice.orders.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class InsertIntoDBRowsCommandLineRunner implements CommandLineRunner{

    private final OrderRepository orderRepository;

    public InsertIntoDBRowsCommandLineRunner(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        int ordersCount = orderRepository.findAll().size();
        log.info("Now at the database are: {} orders", ordersCount);
    }

}
