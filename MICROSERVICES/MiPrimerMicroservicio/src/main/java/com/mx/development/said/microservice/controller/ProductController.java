package com.mx.development.said.microservice.controller;

import com.mx.development.said.microservice.dto.ResponseProduct;
import com.mx.development.said.microservice.service.ProductService;
import com.mx.development.said.microservice.dto.RequestProduct;
import com.mx.development.said.microservice.tool.ProductTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

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

    @GetMapping("/{productoId}")
    public ResponseEntity<ResponseProduct> getProductById(@PathVariable Long productoId) {
        Optional<ResponseProduct> responseOptional = productService.getProductById(productoId);

        return responseOptional
                .map(response -> ResponseEntity.ok(response))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/query")
    public ResponseEntity<List<ResponseProduct>> getProductByStatus(@RequestParam String status) {

        log.info("clase: {}", ProductController.class.getClass().getSimpleName());
        List<ResponseProduct> products = productService.findByStatus(status);

        if (products == null || products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(products);
    }



}
