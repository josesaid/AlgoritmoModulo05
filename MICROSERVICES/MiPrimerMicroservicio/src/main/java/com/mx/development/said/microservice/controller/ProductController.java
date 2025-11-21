package com.mx.development.said.microservice.controller;

import com.mx.development.said.microservice.dto.ResponseProduct;
import com.mx.development.said.microservice.entity.ProductEntity;
import com.mx.development.said.microservice.service.ProductService;
import com.mx.development.said.microservice.dto.RequestProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    //http://localhost:8585/api/v1/products/

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponseProduct>> getAllProducts() {
        List<ResponseProduct> products = productService.findAll();

        if (products == null || products.isEmpty()) {
            log.warn("No products found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        log.info("Number of products found: {}", products.size());
        return ResponseEntity.ok(products);
    }



    @PostMapping("/")
    public ResponseEntity<ResponseProduct> createProduct(@RequestBody RequestProduct product){
        log.info("Creating product: {}", product);
        ResponseProduct response = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*@GetMapping(value = "/{}")
    public RequestProduct getProduct(){
        return productService.getProduct();
    }*/

}
