package com.mx.development.said.microservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public String list() {
        return productService.list();
    }

    @PostMapping("/")
    public RequestProduct createProduct(@RequestBody RequestProduct product){
        return productService.createProduct(product);
    }

    @GetMapping(value = "/getProduct")
    public RequestProduct getProduct(){
        return productService.getProduct();
    }

}
