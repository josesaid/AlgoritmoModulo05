package com.mx.development.sample07.without.spring;

public class ProductRepositoryImpl implements ProductRepositoryI {
    // Simulating database interaction
    @Override
    public Product findById(int productId) {
        // Database query logic here
        // For simplicity, let's return a dummy Product
        //return new Product(productId, "Sample Product", 49.99);
        return new Manzana(productId, "MANZANA", 9.99);
    }
}