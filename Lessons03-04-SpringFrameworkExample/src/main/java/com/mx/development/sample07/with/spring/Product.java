package com.mx.development.sample07.with.spring;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author josesaidolanogarcia
 */
@Data
@AllArgsConstructor
public class Product {
    private int productId;
    private String sampleProduct;
    private double v;
}
