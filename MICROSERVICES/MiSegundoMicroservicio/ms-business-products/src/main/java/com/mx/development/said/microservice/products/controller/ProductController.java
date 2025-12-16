package com.mx.development.said.microservice.products.controller;

import com.mx.development.said.microservice.products.dto.ResponseProduct;
import com.mx.development.said.microservice.products.service.ProductService;
import com.mx.development.said.microservice.products.dto.RequestProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/{productoId}")
    public ResponseEntity<ResponseProduct> updateProduct(@PathVariable Long productoId, @RequestBody RequestProduct requestProduct) {
        log.info("Updating product with ID: {}", productoId);
        ResponseProduct updatedProduct = productService.updateProduct(productoId, requestProduct);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
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
    public ResponseProduct getProductById(@PathVariable Long productoId) {
        return productService.getProductById(productoId);
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


    @DeleteMapping("/{productoId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productoId) {
        log.info("Deleting product with ID: {}", productoId);
        productService.deleteProduct(productoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
