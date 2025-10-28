package com.mx.development.sample07.with.spring;

import org.springframework.stereotype.Component;

/**
 * @author josesaidolanogarcia
 */
@Component
public class ProductRepositoryImpl implements ProductRepositoryI {

    @Override
    public Product findById(int productId) {
        return new Uva(productId, "UVA", 10.11);
    }

}