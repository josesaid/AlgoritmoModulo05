package com.mx.development.said.microservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    public String list() {
        log.info("Listing products...");
        return "List of products*";
    }
    public RequestProduct createProduct(RequestProduct product){
        log.info("Product created: {}", product);
        return product;
    }
    public RequestProduct getProduct(){
        log.info("Getting product...");
        return new RequestProduct("Producto ABC", "Descripcion del producto ABC*");
    }
}
