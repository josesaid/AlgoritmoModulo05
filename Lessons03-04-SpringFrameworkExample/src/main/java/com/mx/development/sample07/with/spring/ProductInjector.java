package com.mx.development.sample07.with.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author josesaidolanogarcia
 */
@SpringBootApplication
public class ProductInjector {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ProductInjector.class, args);

        ProductClient productClient = context.getBean(ProductClient.class);
        productClient.displayProductDetails();
    }
}
