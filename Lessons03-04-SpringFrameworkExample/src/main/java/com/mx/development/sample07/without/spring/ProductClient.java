package com.mx.development.sample07.without.spring;

public class ProductClient {

    //@Autowired
    //private ProductServiceI productService;

    private ProductServiceI productService;
    private InventoryServiceI inventoryService;

    // Constructor Injection
    public ProductClient(ProductServiceI productService, InventoryServiceI inventoryService) {
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    /*
    // Setter Injection
    public void setProductService(ProductServiceI productService) {
        this.productService = productService;
    }*/

    public void displayProductDetails() {
        Product product = productService.getProductById(123);
        System.out.println("Product Details: " + product);

        inventoryService.updateInventory(product);
    }

}