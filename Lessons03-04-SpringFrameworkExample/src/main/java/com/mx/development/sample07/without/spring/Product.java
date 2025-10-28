package com.mx.development.sample07.without.spring;

import lombok.Data;

@Data
public class Product {
    private int productId;
    private String sampleProduct;
    private double v;

    public Product(int productId, String sampleProduct, double v) {
        this.productId = productId;
        this.sampleProduct = sampleProduct;
        this.v = v;
    }
}
