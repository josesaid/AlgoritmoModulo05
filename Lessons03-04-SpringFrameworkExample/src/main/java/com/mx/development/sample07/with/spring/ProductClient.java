package com.mx.development.sample07.with.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author josesaidolanogarcia
 */
@Component
public class ProductClient {

    private ProductServiceI productService;
    private InventoryServiceI inventoryService;

    @Autowired
    public ProductClient(ProductServiceI productService, InventoryServiceI inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    public void displayProductDetails() {
        Product product = productService.getProductById(123);
        System.out.println("Product Details: " + product);
        inventoryService.updateInventory(product);
    }
}