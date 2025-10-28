package com.mx.development.sample07.without.spring;

public class ProductInjector {

    public static void main(String[] args) {

        ProductRepositoryI repositorioDeProductos = new ProductRepositoryImpl();
        ProductServiceI productService = new ProductServiceImpl(repositorioDeProductos);

        InventoryRepositoryI instance = new InventoryRepositoryImpl();
        InventoryServiceI inventoryService = new InventoryServiceImpl(instance);

        ProductClient productClient = new ProductClient(productService, inventoryService);
        productClient.displayProductDetails();
    }

}
